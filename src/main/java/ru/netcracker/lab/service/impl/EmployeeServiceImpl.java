package ru.netcracker.lab.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netcracker.lab.exception.EmployeeNotFoundException;
import ru.netcracker.lab.model.Department;
import ru.netcracker.lab.model.Employee;
import ru.netcracker.lab.repository.DepartmentRep;
import ru.netcracker.lab.repository.EmployeeRep;
import ru.netcracker.lab.service.EmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRep employeeRep;
    private final DepartmentRep departmentRep;

    @Override
    public void add(String name, String phoneNumber, Double salary, long idDep) {
        Department department = departmentRep.getById(idDep);
        Employee employee = new Employee(name, phoneNumber, salary, department);
        employeeRep.save(employee);
//        departmentRep.getById(idDep).setHead(employee);
//        departmentRep.save(department);
//else throw new NullInputEmployeeException("Null Input Employee");
    }

    @Override
    public Employee get(long id) {
        return employeeRep.findById(id).orElseThrow(() -> {
            throw new EmployeeNotFoundException("Employee Not Found");
        });
    }

    public List<Employee> findAll() {
        return employeeRep.findAll();
    }


    @Override
    public void delete(long id) {

    }

    @Override
    public void update(long id) {

    }
}