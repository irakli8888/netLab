package ru.netcracker.lab.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.netcracker.lab.dto.DepartmentDto;
import ru.netcracker.lab.dto.EmployeeDto;
import ru.netcracker.lab.model.api.error.Errors;
import ru.netcracker.lab.model.api.error.MessageForResponse;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResponse {
    private Errors error;
    private LocalDateTime timestamp;
    private DepartmentDto departmentDto;
    private Enum description;

    public DepartmentResponse invalid() {
        error = Errors.INVALID_REQUEST;
        timestamp = LocalDateTime.now();
        return this;
    }

    public DepartmentResponse save(DepartmentDto departmentDto) {
        description = MessageForResponse.SAVE;
        timestamp = LocalDateTime.now();
        this.departmentDto = departmentDto;
        return this;
    }

    public DepartmentResponse delete() {
        description = MessageForResponse.DELETE;
        timestamp = LocalDateTime.now();
        return this;
    }

    public DepartmentResponse update(DepartmentDto departmentDto) {
        description = MessageForResponse.UPDATE;
        timestamp = LocalDateTime.now();
        this.departmentDto = departmentDto;
        return this;
    }

    public DepartmentResponse find(DepartmentDto departmentDto) {
        description = MessageForResponse.FOUND;
        timestamp = LocalDateTime.now();
        this.departmentDto = departmentDto;
        return this;
    }
}
