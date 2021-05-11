package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;

public class CollectionsMain {

    public static void main(String[] args) {
            // Factory Method - Abstract Factory tervezési minta
            EntityManagerFactory entityManagerFactory =
                    Persistence.createEntityManagerFactory("pu");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            Employee employee = new Employee("John Doe");
            employee.getNickNames().addAll(Arrays.asList("John", "Johnny", "Little John"));

            employee.getVacationBookings().add(new VacationEntry(LocalDate.of(2021, 6, 1), 10));

            employee.getPhoneNumbers().put("home", "1234567");
            employee.getPhoneNumbers().put("work", "7654321");

            entityManager.persist(employee);

            entityManager.getTransaction().commit();

            entityManager.close();
        }
}
