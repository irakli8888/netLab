package ru.netcracker.lab.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netcracker.lab.dto.DepartmentDto;
import ru.netcracker.lab.mapper.DepartmentMapper;
import ru.netcracker.lab.model.Department;
import ru.netcracker.lab.model.Employee;
import ru.netcracker.lab.model.api.request.DepartmentRequest;
import ru.netcracker.lab.model.api.response.DepartmentResponse;
import ru.netcracker.lab.model.api.response.DepartmentResponseWithList;
import ru.netcracker.lab.repository.DepartmentRep;
import ru.netcracker.lab.repository.EmployeeRep;
import ru.netcracker.lab.repository.specification.DepartmentSpecificationHolder;
import ru.netcracker.lab.repository.specification.SearchCriteria;
import ru.netcracker.lab.repository.specification.SearchOperation;
import ru.netcracker.lab.service.DepartmentService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeRep employeeRep;
    private final DepartmentRep departmentRep;
    private final DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    @Override
    public ResponseEntity<DepartmentResponse> save(DepartmentRequest request) {
        if(request.getDepartmentName() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new DepartmentResponse().invalid());
        }
        Department department = new Department();
        department.setName(request.getDepartmentName());
        if(request.getChiefName() != null){
            if (employeeRep.existsByFullName(request.getChiefName())){
                return ResponseEntity
                        .badRequest()
                        .body(new DepartmentResponse().invalid());
            }else{
                Employee employee = new Employee();
                employee.setFullName(request.getChiefName());
                department.setChief(employee);
                employeeRep.save(employee);
            }
        }
        if(request.getEmployees() != null){
            Set<Employee> employees = new HashSet<>();
            if (request.getEmployees().stream().anyMatch(item->
                employeeRep.existsByFullName(item.getFullName())
            )) {
                return ResponseEntity
                        .badRequest()
                        .body(new DepartmentResponse().invalid());
            }else {
                request.getEmployees().forEach(item->{
                    Employee employee = new Employee();
                    employee.setFullName(item.getFullName());
                    employee.setPhoneNumber(item.getPhoneNumber());
                    employee.setSalary(item.getSalary());
                    employee.setDepartment(department);
                    employees.add(employee);
                    employeeRep.save(employee);
                });
                department.setEmployees(employees);
            }
        }
        departmentRep.save(department);
        return ResponseEntity
                .ok()
                .body(new DepartmentResponse().save(departmentMapper.convert(department)));
    }

    @Override
    public ResponseEntity<DepartmentResponse> delete(Long id) {
        if(departmentRep.existsById(id)){
            departmentRep.deleteById(id);
            return ResponseEntity
                    .ok()
                    .body(new DepartmentResponse().delete());
        }
        return ResponseEntity
                .badRequest()
                .body(new DepartmentResponse().invalid());
    }

    @Override
    public ResponseEntity<DepartmentResponse> update(DepartmentRequest request, Long id) {
        if(request.getDepartmentName() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new DepartmentResponse().invalid());
        }
        Department department = departmentRep.findById(id).get();
        department.setName(request.getDepartmentName());
        if(request.getChiefName() != null){
            if(employeeRep.existsByFullName(request.getChiefName())){
                Employee employee = employeeRep.findByFullName(request.getChiefName());
                department.setChief(employee);
            }else {
                Employee employee = new Employee();
                employee.setFullName(request.getChiefName());
                department.setChief(employee);
            }
        }
        if(request.getEmployees() != null){
            Set<Employee> employees = new HashSet<>();
            if (request.getEmployees().stream().anyMatch(item->
                    employeeRep.existsByFullName(item.getFullName())
            )) {
                request.getEmployees().forEach(item->{
                    Employee employee = employeeRep.findByFullName(item.getFullName());
                    employees.add(employee);
                });
                department.setEmployees(employees);
            }else {
                request.getEmployees().forEach(item->{
                    Employee employee = new Employee();
                    employee.setFullName(item.getFullName());
                    employee.setPhoneNumber(item.getPhoneNumber());
                    employee.setSalary(item.getSalary());
                    employee.setDepartment(department);
                    employees.add(employee);
                });
                department.setEmployees(employees);
            }
        }
        departmentRep.save(department);
        return ResponseEntity
                .ok()
                .body(new DepartmentResponse().update(departmentMapper.convert(department)));
    }

    @Override
    public ResponseEntity<DepartmentResponse> findById(Long id) {
        if(departmentRep.existsById(id)){
            return ResponseEntity
                    .ok()
                    .body(new DepartmentResponse().find(departmentMapper.convert(departmentRep.findById(id).get())));
        }else return ResponseEntity
                .badRequest()
                .body(new DepartmentResponse().invalid());
    }

    @Override
    public ResponseEntity<DepartmentResponseWithList> findAll(){
        Set<DepartmentDto> departmentDtoSet = departmentRep
                .findAll()
                .stream()
                .map(departmentMapper::convert)
                .collect(Collectors.toSet());
        return ResponseEntity
                .ok()
                .body(new DepartmentResponseWithList().findAll(departmentDtoSet));
    }

    @Override
    public ResponseEntity<DepartmentResponseWithList> findAllByCriteria(DepartmentRequest request){
        DepartmentSpecificationHolder departmentSpecificationHolder = new DepartmentSpecificationHolder();
        departmentSpecificationHolder.add(new SearchCriteria("departmentName", request.getDepartmentName(), SearchOperation.MATCH));
        Set<DepartmentDto> departmentDtoSet = departmentRep
                .findAll(departmentSpecificationHolder)
                .stream()
                .map(departmentMapper::convert)
                .collect(Collectors.toSet());
        return ResponseEntity
                .ok()
                .body(new DepartmentResponseWithList().findAll(departmentDtoSet));
    }
}
