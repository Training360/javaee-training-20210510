package employees;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeesService {

    @Inject
    private EmployeesDao employeesDao;

    @Inject
    private Event<SomethingHasHappenedEvent> eventPublisher;

    public void save() {
        System.out.println("EmployeesService.save");
        employeesDao.save();
    }

    public void timerExpired() {
        System.out.println("expired");
        eventPublisher.fire(new SomethingHasHappenedEvent("xxx"));
    }
}
