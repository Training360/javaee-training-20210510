package employees;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TransferService {

    @EJB
    private EmployeesService employeesService;

    public void transfer(TransferCommand command) {
        employeesService.credit(new CreditCommand(command.getSourceId(), -command.getDiff()));
        employeesService.credit(new CreditCommand(command.getDestinationId(), command.getDiff()));
    }
}
