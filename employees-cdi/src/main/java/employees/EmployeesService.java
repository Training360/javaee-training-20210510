package employees;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeesService {

    @Inject
    private EmployeesDao employeesDao;

    public void save() {
        System.out.println("EmployeesService.save");
        employeesDao.save();
    }
}
