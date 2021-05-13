package employees;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class EmployeesMain {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            EmployeesService employeesService = container.select(EmployeesService.class).get();

            employeesService.save();
        }
    }
}
