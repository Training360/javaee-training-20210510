package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {
        // Factory Method - Abstract Factory tervezési minta
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("pu");

        new DbInit().init(entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> added = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            Employee employee = new Employee("John Doe");
            added.add(employee);
            entityManager.persist(employee);

            // Hibernate itt már kiosztja az azonosítókat
            System.out.println(employee);
        }

        System.out.println("Load back");

        List<Employee> employeesLoadad = entityManager
                .createQuery("select e from Employee e", Employee.class)
                .getResultList();

        System.out.println(employeesLoadad);

        System.out.println("Find by id");
        Employee employee = entityManager.find(Employee.class, added.get(0).getId());
        System.out.println(employee);

        entityManager.getTransaction().commit();

        entityManager.clear();

        System.out.println("Query after commit");

        // EclipseLink csak itt osztja ki az azonosítókat
        added.forEach(e -> System.out.println(e));

        List<Employee> employees = entityManager
                .createQuery("select e from Employee e", Employee.class)
                .getResultList();

        employees.forEach(e -> System.out.println(e));

        entityManager.close();

    }
}
