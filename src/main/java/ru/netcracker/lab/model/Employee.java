package ru.netcracker.lab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author IrklKvch
 * @author AverVit
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phoneNumber;

    private double salary;

    @ManyToOne
    private Department department;

    @OneToOne(mappedBy = "chief", cascade = CascadeType.ALL)
    private Department managedDepartment;

    public Employee(String fullName, String phoneNumber, double salary, Department department) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.department = department;
    }
}
