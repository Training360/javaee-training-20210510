package employees;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class TransferService {

    @Inject
    private EmployeesService employeesService;

    @Inject
    private LogService logService;

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
