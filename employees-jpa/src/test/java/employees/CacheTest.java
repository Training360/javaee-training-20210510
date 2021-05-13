package employees;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CacheTest {

    @Test
    void testCache() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

        new DbInit().init(entityManagerFactory);
        deleteAll(entityManagerFactory);
        EmployeesDao employeesDao = new EmployeesDao(entityManagerFactory);

        Employee employee = new Employee("John Doe");
        employeesDao.save(employee);

        employee = employeesDao.findById(employee.getId());
        System.out.println(entityManagerFactory.getCache().contains(Employee.class, employee.getId()));
        System.out.println(employee.getName());

        entityManagerFactory.getCache().evictAll();

        employee = employeesDao.findById(employee.getId());
        System.out.println(employee.getName());

    }

    void deleteAll(EntityManagerFactory entityManagerFactory) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Address a").executeUpdate();
        em.createQuery("delete from Employee e").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
