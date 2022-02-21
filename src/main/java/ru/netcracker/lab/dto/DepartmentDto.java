package ru.netcracker.lab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.netcracker.lab.model.api.request.EmployeeRequest;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDto {

    private String name;
    private String chief;
    private Set<EmployeeRequest> employees;
}
