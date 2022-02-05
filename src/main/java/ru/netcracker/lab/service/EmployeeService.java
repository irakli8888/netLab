package ru.netcracker.lab.service;

import ru.netcracker.lab.model.Employee;

public interface EmployeeService {
    void add(String name, String phoneNumber, Double salary, long idDep);
    Employee get(long id);
    void delete(long id);
    void update(long id);
}
