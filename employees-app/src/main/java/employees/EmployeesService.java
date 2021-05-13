package employees;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class EmployeesService {

    @EJB
    private EmployeesDao employeesDao;

    public EmployeeDto save(CreateEmployeeCommand command) {
        // Mapper
        Employee employee = new Employee();
        employee.setName(command.getName());
        employee.setAmount(command.getAmount());
        employeesDao.save(employee);

        return toDto(employee);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public List<EmployeeDto> findAll() {
//        return employeesDao.findAll().stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());

        return employeesDao.findAllDto();
    }

    private EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAmount(employee.getAmount());
        return dto;
    }

    public EmployeeDto credit(CreditCommand command) {
        Employee employee = employeesDao.findById(command.getEmployeeId());
        employee.setAmount(employee.getAmount() + command.getDiff());
        return toDto(employee);
    }
}
