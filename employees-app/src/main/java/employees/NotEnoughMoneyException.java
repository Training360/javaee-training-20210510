package employees;

import javax.ejb.ApplicationException;

// @ApplicationException(rollback = false)
public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
