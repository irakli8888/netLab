package ru.netcracker.lab.model.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeRequest {

    private String departmentName;

    private String managedDepartmentName;

    private String fullName;

    private String phoneNumber;

    private double salary;
}
