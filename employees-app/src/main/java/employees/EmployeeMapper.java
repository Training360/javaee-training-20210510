package employees;

import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEntity(CreateEmployeeCommand command);
}
