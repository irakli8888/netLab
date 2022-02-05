package ru.netcracker.lab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {

    private String fullName;

    private String phoneNumber;

    private double salary;

    private String department;

    private String managedDepartment;
}
