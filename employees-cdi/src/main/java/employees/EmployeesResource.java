package employees;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeesResource {

    @Inject
    private EmployeesService employeesService;

    public void handleEvent(@Observes SomethingHasHappenedEvent event) {
        System.out.println(event.getMessage());
    }
}
