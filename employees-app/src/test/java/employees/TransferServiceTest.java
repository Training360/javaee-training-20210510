package employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {

    @Mock
    EmployeesService employeesService;

    @InjectMocks
    TransferService transferService;

    @Test
    void testTransfer() throws NotEnoughMoneyException {
        transferService.transfer(new TransferCommand(1, 2, 500));

        Mockito.verify(employeesService).credit(argThat(c -> c.getEmployeeId() == 1 && c.getDiff() == -500));
        Mockito.verify(employeesService).credit(argThat(c -> c.getEmployeeId() == 2 && c.getDiff() == 500));

    }
}
