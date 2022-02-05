package ru.netcracker.lab.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author IrklKvch
 * @author AverVit
 */
@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phoneNumber;

    private double salary;

    public Employee(String fullName, String phoneNumber, double salary, Department department) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.department = department;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
}
