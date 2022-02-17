package ru.netcracker.lab.model.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netcracker.lab.dto.EmployeeDto;

import java.util.Set;

@Data
public class DepartmentRequest {

    private String chiefName;

    private Set<EmployeeRequest> employees;

    private String departmentName;

}
