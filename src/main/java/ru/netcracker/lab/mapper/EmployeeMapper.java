package ru.netcracker.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.netcracker.lab.dto.EmployeeDto;
import ru.netcracker.lab.model.Employee;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface EmployeeMapper {

    @Mapping(target = "department", source = "employee.department.name")
    @Mapping(target = "managedDepartment", source = "employee.managedDepartment.name")
    EmployeeDto convert(Employee employee);
}
