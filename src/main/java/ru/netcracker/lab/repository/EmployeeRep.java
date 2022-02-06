package ru.netcracker.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.lab.model.Department;
import ru.netcracker.lab.model.Employee;

import java.util.Set;

@Repository
public interface EmployeeRep extends JpaRepository<Employee, Long> {

//    boolean existsByName(String name);
//    Set<Employee> findByName(String name);

}
