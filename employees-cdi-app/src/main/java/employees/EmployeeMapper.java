package employees;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEntity(CreateEmployeeCommand command);
}
