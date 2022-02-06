package ru.netcracker.lab.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.netcracker.lab.dto.DepartmentDto;
import ru.netcracker.lab.model.Department;


@Mapper(uses = EmployeeMapper.class)
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    //@Mapping(target = "chief", source = "department.chief.fullName")//?
    //@Mapping(target = "employees", source = "department.employees")//?
    DepartmentDto convert(Department department);
}
