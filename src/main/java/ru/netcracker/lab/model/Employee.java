package ru.netcracker.lab.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author IrklKvch
 * @author AverVit
 */
@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    private String phoneNumber;

    private double salary;

}
