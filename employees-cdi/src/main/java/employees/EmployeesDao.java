package employees;

import javax.inject.Named;

@Named
public class EmployeesDao {

    public void save() {
        System.out.println("EmployeesDao.save");
    }
}
