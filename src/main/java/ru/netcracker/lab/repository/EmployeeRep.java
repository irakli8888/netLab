package ru.netcracker.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.netcracker.lab.model.Employee;

@Repository
public interface EmployeeRep extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    boolean existsByFullName(String name);

    Employee findByFullName(String name);
}
