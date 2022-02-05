package ru.netcracker.lab.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netcracker.lab.exception.DepartmentNotFoundException;
import ru.netcracker.lab.model.Department;
import ru.netcracker.lab.model.Employee;
import ru.netcracker.lab.repository.DepartmentRep;
import ru.netcracker.lab.repository.EmployeeRep;
import ru.netcracker.lab.service.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeRep employeeRep;
    private final DepartmentRep departmentRep;

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
}
