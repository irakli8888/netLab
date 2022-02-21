package ru.netcracker.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.netcracker.lab.dto.DepartmentDto;
import ru.netcracker.lab.model.Department;

@Mapper(componentModel = "spring", uses = EmployeeMapper.class)
public interface DepartmentMapper {

    @Mapping(target = "chief", source = "department.chief.fullName")
    @Mapping(target = "employees", source = "department.employees")
    DepartmentDto convert(Department department);
}
