package employees;

import org.h2.jdbcx.JdbcDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {
        // Factory Method - Abstract Factory tervezési minta
        //DataSource source = new JdbcDataSource();

        //
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Employee employee = new Employee("John Doe");
        entityManager.persist(employee);

        entityManager.getTransaction().commit();

        List<Employee> employees = entityManager
                .createQuery("select e from Employee e", Employee.class)
                .getResultList();

        employees.forEach(e -> System.out.println(e.getName()));

    }
}
