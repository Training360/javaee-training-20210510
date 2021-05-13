package employees;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EmployeesDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Employee employee) {
        entityManager.persist(employee);
    }

    public List<Employee> findAll() {
        return entityManager.createQuery("select e from Employee e order by e.name", Employee.class)
                .setHint("org.hibernate.cacheable", true)
                .getResultList();
    }

    public List<EmployeeDto> findAllDto() {
        return entityManager.createQuery("select new employees.EmployeeDto(e.id, e.name, e.amount) from Employee e order by e.name")
        .setHint("org.hibernate.cacheable", true)
                .getResultList();
    }

    public Employee findById(long employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }
}
