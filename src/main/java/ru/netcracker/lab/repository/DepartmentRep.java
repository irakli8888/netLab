package ru.netcracker.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.netcracker.lab.model.Department;

import java.util.Optional;

@Repository
public interface DepartmentRep extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

    boolean existsByName(String name);
    Optional<Department> findByName(String name);
}
