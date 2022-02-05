package ru.netcracker.lab.model.api.request;

import lombok.Data;


@Data
public class EmployeeRequest {

    private String departmentName;

    private String managedDepartmentName;

    private String fullName;

    private String phoneNumber;

    private double salary;
}
