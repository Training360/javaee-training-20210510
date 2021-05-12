package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class ConnectionMain {

    public static void main(String[] args) {
            EntityManagerFactory entityManagerFactory =
                    Persistence.createEntityManagerFactory("pu");

            new DbInit().init(entityManagerFactory);

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            Employee employee = new Employee("John Doe");
            Address address1 = new Address("Budapest");
            employee.addAddress(address1);
            Address address2 = new Address("Zirc");
            employee.addAddress(address2);

            entityManager.persist(employee);

//            entityManager.persist(address1);
//            entityManager.persist(address2);

            entityManager.getTransaction().commit();

            //List<Employee> loadedEmployees = entityManager.createQuery("select distinct e from Employee e left join fetch e.nickNames", Employee.class).getResultList();
            List<Employee> loadedEmployees = entityManager.createQuery("select distinct e from Employee e left join fetch e.nickNames order by e.name", Employee.class).getResultList();
            //loadedEmployees.stream().flatMap(e -> e.getNickNames().stream()).forEach(System.out::println);
            System.out.println(loadedEmployees.size());

            entityManager.close();
        }
}
