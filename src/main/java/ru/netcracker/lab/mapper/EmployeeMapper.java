package ru.netcracker.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.netcracker.lab.dto.EmployeeDto;
import ru.netcracker.lab.model.Employee;

@Mapper(uses = DepartmentMapper.class)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    @Mapping(target = "department", source = "employee.department.name")
    @Mapping(target = "managedDepartment", source = "employee.managedDepartment.name")
    EmployeeDto convert(Employee employee);
}
