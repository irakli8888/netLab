package ru.netcracker.lab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDto {

    private String name;
    private Set<EmployeeDto> employees;
}
