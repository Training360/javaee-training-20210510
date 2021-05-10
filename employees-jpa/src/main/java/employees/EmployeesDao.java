package employees;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@AllArgsConstructor
public class EmployeesDao {

    private EntityManagerFactory entityManagerFactory;

    public void save(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    public List<Employee> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();
        em.close();
        return employees;
    }

    // TODO Implementálni - írjatok rá egy tesztesetet
    public void update(long id, String newName) {

    }
}
