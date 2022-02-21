package ru.netcracker.lab.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.netcracker.lab.dto.EmployeeDto;
import ru.netcracker.lab.model.api.error.MessageForResponse;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseWithList extends EmployeeResponse {

    private Set<EmployeeDto> employees;

    public EmployeeResponseWithList findAll(Set<EmployeeDto> employees) {
        setDescription(MessageForResponse.SAVE);
        setTimestamp(LocalDateTime.now());
        this.employees = employees;
        return this;
    }


}
