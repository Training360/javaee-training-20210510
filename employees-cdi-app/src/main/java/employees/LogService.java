package employees;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class LogService {

    @Inject
    private LogEntryRepository logEntryRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void logMessage(String message) {
        logEntryRepository.save(new LogEntry(message));
    }
}
