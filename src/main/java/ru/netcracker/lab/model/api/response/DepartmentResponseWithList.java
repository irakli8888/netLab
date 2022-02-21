package ru.netcracker.lab.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.netcracker.lab.dto.DepartmentDto;
import ru.netcracker.lab.model.api.error.MessageForResponse;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResponseWithList extends DepartmentResponse {

    private Set<DepartmentDto> departments;

    public DepartmentResponseWithList findAll(Set<DepartmentDto> departments) {
        setDescription(MessageForResponse.SAVE);
        setTimestamp(LocalDateTime.now());
        this.departments = departments;
        return this;
    }
}
