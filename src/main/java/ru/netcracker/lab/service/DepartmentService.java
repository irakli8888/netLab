package ru.netcracker.lab.service;

import ru.netcracker.lab.model.Department;

public interface DepartmentService {
    void add(String name, long headId);
    Department get(long id);
    void delete(long id);
    void update(long id);
}