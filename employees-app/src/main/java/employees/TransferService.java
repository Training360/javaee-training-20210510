package employees;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.transaction.UserTransaction;

@Stateless
public class TransferService {

    @EJB
    private EmployeesService employeesService;

    @EJB
    private LogService logService;

    @Resource
    private SessionContext sessionContext;

    public boolean transfer(TransferCommand command) {
        try {
            System.out.println(logService);

            log(command);

            employeesService.credit(new CreditCommand(command.getDestinationId(), command.getDiff()));
            // Levonás legyen a második művelet!
            employeesService.credit(new CreditCommand(command.getSourceId(), -command.getDiff()));
            return true;
        }
        catch (NotEnoughMoneyException neme) {
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public void log(TransferCommand command) {
        logService.logMessage(String.format("Transfer from %d to %d, amount: %d",
                command.getSourceId(),
                command.getDestinationId(),
                command.getDiff()));
    }
}
