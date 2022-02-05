package ru.netcracker.lab.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.netcracker.lab.dto.EmployeeDto;
import ru.netcracker.lab.model.api.error.Errors;
import ru.netcracker.lab.model.api.error.MessageForResponse;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {

    private Errors error;
    private LocalDateTime timestamp;
    private EmployeeDto employeeDto;
    private Enum description;

    public EmployeeResponse invalid(){
        error = Errors.INVALID_REQUEST;
        timestamp = LocalDateTime.now();
        return this;
    }

    public EmployeeResponse save(EmployeeDto employeeDto) {
        description = MessageForResponse.SAVE;
        timestamp = LocalDateTime.now();
        this.employeeDto = employeeDto;
        return this;
    }

    public EmployeeResponse delete() {
        description = MessageForResponse.DELETE;
        timestamp = LocalDateTime.now();
        return this;
    }

    public EmployeeResponse update(EmployeeDto employeeDto) {
        description = MessageForResponse.UPDATE;
        timestamp = LocalDateTime.now();
        this.employeeDto = employeeDto;
        return this;
    }

    public EmployeeResponse find(EmployeeDto employeeDto) {
        description = MessageForResponse.FOUND;
        timestamp = LocalDateTime.now();
        this.employeeDto = employeeDto;
        return this;
    }

}
