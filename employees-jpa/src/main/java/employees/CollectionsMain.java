package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CollectionsMain {

    public static void main(String[] args) {
            // Factory Method - Abstract Factory tervezési minta
            EntityManagerFactory entityManagerFactory =
                    Persistence.createEntityManagerFactory("pu");

            new DbInit().init(entityManagerFactory);

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            for (int i = 0; i < 10; i ++) {
                    Employee employee = new Employee("John Doe " + i);

                    // Clean Code
                    // Train wreck - vonatkarambol
                    // Demeter törvényét
//            employee.getNickNames().addAll(Arrays.asList("John", "Johnny", "Little John"));
                    employee.addNickNames("John", "Johnny", "Little John");

                    employee.getVacationBookings().add(new VacationEntry(LocalDate.of(2021, 6, 1), 10));

                    employee.getPhoneNumbers().put("home", "1234567");
                    employee.getPhoneNumbers().put("work", "7654321");

                    entityManager.persist(employee);
            }

            entityManager.getTransaction().commit();

            entityManager.clear();

//            System.out.println(entityManager.contains(employee));
//
//            Employee loaded = entityManager.find(Employee.class, employee.getId());
//            System.out.println(loaded.getName());
//            System.out.println(loaded.getNickNames());
//
//            loaded.getNickNames().forEach(System.out::println);
//
//            System.out.println(loaded.getPhoneNumbers());
//
//            entityManager.clear();

            System.out.println("*** Load all employees");
            //List<Employee> loadedEmployees = entityManager.createQuery("select distinct e from Employee e left join fetch e.nickNames", Employee.class).getResultList();
            List<Employee> loadedEmployees = entityManager.createQuery("select distinct e from Employee e left join fetch e.nickNames order by e.name", Employee.class).getResultList();
            //loadedEmployees.stream().flatMap(e -> e.getNickNames().stream()).forEach(System.out::println);
            System.out.println(loadedEmployees.size());

            entityManager.close();
        }
}
