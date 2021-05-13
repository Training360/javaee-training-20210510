package employees;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface LogEntryRepository extends EntityRepository<LogEntry, Long> {
}
