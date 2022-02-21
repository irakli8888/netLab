package ru.netcracker.lab.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netcracker.lab.dto.EmployeeDto;
import ru.netcracker.lab.mapper.EmployeeMapper;
import ru.netcracker.lab.model.Department;
import ru.netcracker.lab.model.Employee;
import ru.netcracker.lab.model.api.request.EmployeeRequest;
import ru.netcracker.lab.model.api.response.EmployeeResponse;
import ru.netcracker.lab.model.api.response.EmployeeResponseWithList;
import ru.netcracker.lab.repository.DepartmentRep;
import ru.netcracker.lab.repository.EmployeeRep;
import ru.netcracker.lab.repository.specification.EmployeeSpecificationHolder;
import ru.netcracker.lab.repository.specification.SearchCriteria;
import ru.netcracker.lab.repository.specification.SearchOperation;
import ru.netcracker.lab.service.EmployeeService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRep employeeRep;
    private final DepartmentRep departmentRep;
    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @Override
    public ResponseEntity<EmployeeResponse> save(EmployeeRequest request) {
        if(request.getFullName() == null || request.getSalary() == 0 || request.getPhoneNumber() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new EmployeeResponse().invalid());
        }
        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setSalary(request.getSalary());
        employee.setPhoneNumber(request.getPhoneNumber());
        if(request.getDepartmentName() != null){
            if (departmentRep.existsByName(request.getDepartmentName())){
                return ResponseEntity
                        .badRequest()
                        .body(new EmployeeResponse().invalid());
            }else{
                Department department = new Department();
                department.setName(request.getDepartmentName());
                employee.setDepartment(department);
                departmentRep.save(department);
            }
        }
        if(request.getManagedDepartmentName() != null){
            if (departmentRep.existsByName(request.getManagedDepartmentName())){
               Department department = departmentRep.findByName(request.getManagedDepartmentName()).get();
               department.setChief(employee);
               employee.setManagedDepartment(department);
                departmentRep.save(department);

            }else{
                Department department = new Department();
                department.setName(request.getManagedDepartmentName());
                department.setChief(employee);
                employee.setManagedDepartment(department);
                departmentRep.save(department);
            }
        }

        employeeRep.save(employee);
        return ResponseEntity
                .ok()
                .body(new EmployeeResponse().save(employeeMapper.convert(employee)));
    }

    @Override
    public ResponseEntity<EmployeeResponse> delete(Long id) {
        if(employeeRep.existsById(id)){

            employeeRep.deleteById(id);
            return ResponseEntity
                    .ok()
                    .body(new EmployeeResponse().delete());
        }
        return ResponseEntity
                .badRequest()
                .body(new EmployeeResponse().invalid());
    }

    @Override
    public ResponseEntity<EmployeeResponse> update(EmployeeRequest request, Long id) {
/*        if(employeeRep.existsById(id)){
            employeeRep.deleteById(id);
            return ResponseEntity
                    .ok()
                    .body(new EmployeeResponse().delete());
        }*/
        if(request.getFullName() == null || request.getSalary() == 0 || request.getPhoneNumber() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new EmployeeResponse().invalid());
        }
        Employee employee = employeeRep.findById(id).get();
        employee.setSalary(request.getSalary());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setFullName(request.getFullName());
        if(request.getDepartmentName() != null){
            if(departmentRep.existsByName(request.getDepartmentName())){
                Department department = departmentRep.findByName(request.getDepartmentName()).get();
                employee.setDepartment(department);
            }else {
                Department department = new Department();
                department.setName(request.getDepartmentName());
                employee.setDepartment(department);
            }
        }
        if(request.getManagedDepartmentName() != null){
            if (departmentRep.existsByName(request.getDepartmentName())){
                Department department = departmentRep.findByName(request.getManagedDepartmentName()).get();
                department.setChief(employee);
                employee.setManagedDepartment(department);
            }else{
                Department department = new Department();
                department.setName(request.getDepartmentName());
                employee.setManagedDepartment(department);
            }
        }
        employeeRep.save(employee);
        return ResponseEntity
                .ok()
                .body(new EmployeeResponse().update(employeeMapper.convert(employee)));
    }

    @Override
    public ResponseEntity<EmployeeResponse> findById(Long id) {
        if(employeeRep.existsById(id)){
            return ResponseEntity
                    .ok()
                    .body(new EmployeeResponse().find(employeeMapper.convert(employeeRep.findById(id).get())));
        }else return ResponseEntity
                .badRequest()
                .body(new EmployeeResponse().invalid());
    }

    @Override
    public ResponseEntity<EmployeeResponseWithList> findAll(){
        Set<EmployeeDto> employeeDtoSet = employeeRep
                .findAll()
                .stream()
                .map(employeeMapper::convert)
                .collect(Collectors.toSet());
        return ResponseEntity
                .ok()
                .body(new EmployeeResponseWithList().findAll(employeeDtoSet));

    }

    @Override
    public ResponseEntity<EmployeeResponseWithList> findAllByCriteria(EmployeeRequest request){
        EmployeeSpecificationHolder employeeSpecificationHolder = new EmployeeSpecificationHolder();
        employeeSpecificationHolder.add(new SearchCriteria("fullName", request.getFullName(), SearchOperation.MATCH));
        Set<EmployeeDto> employeeDtoSet = employeeRep
                .findAll(employeeSpecificationHolder)
                .stream()
                .map(employeeMapper::convert)
                .collect(Collectors.toSet());
        return ResponseEntity
                .ok()
                .body(new EmployeeResponseWithList().findAll(employeeDtoSet));
    }
}