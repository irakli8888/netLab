package ru.netcracker.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.lab.model.Department;

@Repository
public interface DepartmentRep extends JpaRepository<Department, Long> {

    boolean existsByName(String name);
    Department findByName(String name);
}
