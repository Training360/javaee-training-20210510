package employees;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.transaction.UserTransaction;
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

    //@Asynchronous
    public void credit(CreditCommand command) throws NotEnoughMoneyException {
        System.out.println(Thread.currentThread().getName());

        Employee employee = employeesDao.findById(command.getEmployeeId());
        if (employee.getAmount() + command.getDiff() < 0) {
            throw new NotEnoughMoneyException("Can not credit");
        }
        employee.setAmount(employee.getAmount() + command.getDiff());
        //return mapper.toDto(employee);
    }
}
