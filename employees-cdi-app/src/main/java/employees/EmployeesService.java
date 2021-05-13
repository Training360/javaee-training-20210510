package employees;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeesService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private EmployeeMapper mapper;

    @Transactional
    public EmployeeDto save(CreateEmployeeCommand command) {
        // Mapper
        Employee employee = mapper.toEntity(command);
        employeeRepository.save(employee);

        return mapper.toDto(employee);
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAllOrderByName().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    //@Asynchronous
    public void credit(CreditCommand command) throws NotEnoughMoneyException {
        System.out.println(Thread.currentThread().getName());

        Employee employee = employeeRepository.findBy(command.getEmployeeId());
        if (employee.getAmount() + command.getDiff() < 0) {
            throw new NotEnoughMoneyException("Can not credit");
        }
        employee.setAmount(employee.getAmount() + command.getDiff());
        //return mapper.toDto(employee);
    }
}
