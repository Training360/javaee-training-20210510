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

    private EmployeeMapper mapper = new EmployeeMapperImpl();

    public EmployeeDto save(CreateEmployeeCommand command) {
        // Mapper
        Employee employee = mapper.toEntity(command);
        employeesDao.save(employee);

        return mapper.toDto(employee);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public List<EmployeeDto> findAll() {
//        return employeesDao.findAll().stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());

        return employeesDao.findAllDto();
    }

    public EmployeeDto credit(CreditCommand command) {
        Employee employee = employeesDao.findById(command.getEmployeeId());
        employee.setAmount(employee.getAmount() + command.getDiff());
        return mapper.toDto(employee);
    }
}
