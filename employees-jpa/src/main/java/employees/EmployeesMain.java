package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {
        // Factory Method - Abstract Factory tervezési minta
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        for (int i = 0; i < 10; i ++) {
            Employee employee = new Employee("John Doe");
            entityManager.persist(employee);
            System.out.println(employee.getId());
        }
        entityManager.getTransaction().commit();

        List<Employee> employees = entityManager
                .createQuery("select e from Employee e", Employee.class)
                .getResultList();

        employees.forEach(e -> System.out.println(e.getName()));

    }
}
