package ru.netcracker.lab.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netcracker.lab.exception.DepartmentNotFoundException;
import ru.netcracker.lab.mapper.DepartmentMapper;
import ru.netcracker.lab.mapper.EmployeeMapper;
import ru.netcracker.lab.model.Department;
import ru.netcracker.lab.model.Employee;
import ru.netcracker.lab.model.api.request.DepartmentRequest;
import ru.netcracker.lab.model.api.request.EmployeeRequest;
import ru.netcracker.lab.model.api.response.DepartmentResponse;
import ru.netcracker.lab.model.api.response.EmployeeResponse;
import ru.netcracker.lab.repository.DepartmentRep;
import ru.netcracker.lab.repository.EmployeeRep;
import ru.netcracker.lab.service.DepartmentService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeRep employeeRep;
    private final DepartmentRep departmentRep;
    private final DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    @Override
    public void add(String name, long head) {
        Employee employee = employeeRep.getById(head);
//        Department department = new Department(name, employee);
//        departmentRep.save(department);
    }

    @Override
    public Department get(long id) {
        return departmentRep.findById(id).orElseThrow(()-> {
            throw new DepartmentNotFoundException("Department Not Found");
        });
    }

    public List<Department> findAll(){
        return departmentRep.findAll();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(long id) {

    }

//    public ResponseEntity<DepartmentResponse> save(DepartmentRequest request) {
//        if(request.getName() == null){
//            return ResponseEntity
//                    .badRequest()
//                    .body(new DepartmentResponse().invalid());
//        }
//        Department department = new Department();
//        department.setName(request.getName());
//        if(request.getChiefName() != null){
//            if (employeeRep.existsByName(request.getChiefName())){
//                return ResponseEntity
//                        .badRequest()
//                        .body(new DepartmentResponse().invalid());
//            }else{
//                Employee employee = new Employee();
//                employee.setFullName(request.getChiefName());
//                department.setChief(employee);
//                employeeRep.save(employee);
//            }
//        }
//
//        if(request.getEmployees() != null){
//            //?
//        }
//
//        departmentRep.save(department);
//        return ResponseEntity
//                .ok()
//                .body(new DepartmentResponse().save(departmentMapper.convert(department)));
//    }
}
