package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeesDaoTest {

    EmployeesDao employeesDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        new DbInit().init(entityManagerFactory);
        deleteAll(entityManagerFactory);
        employeesDao = new EmployeesDao(entityManagerFactory);
    }

    void deleteAll(EntityManagerFactory entityManagerFactory) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Address a").executeUpdate();
        em.createQuery("delete from Employee e").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    @DisplayName("When save then should find employee")
    void test_when_save_then_find_all_should_find_employee() {
        // Given
        Employee employee = new Employee("John Doe");

        // When
        employeesDao.save(employee);

        // Then
        List<Employee> employees = employeesDao.findAll();

        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    void test_save() {
        // Given
        Employee employee = new Employee("John Doe");

        // When
        employeesDao.save(employee);
        long id = employee.getId();
        System.out.println("ID: " + id);

        // Then
//        List<Employee> employees = employeesDao.findAll();
//        assertEquals("John Doe",
//                employees.stream().filter(e -> e.getId() == id).findAny().get().getName());

        Employee loaded = employeesDao.findById(id);
        assertEquals("John Doe", loaded.getName());
    }

    @Test
    void test_save_employees() {
        // When
        employeesDao.save(new Employee("John Doe"));
        employeesDao.save(new Employee("Jane Doe"));

        // Then
        List<Employee> employees = employeesDao.findAll();
//        assertEquals(Arrays.asList("John Doe", "Jane Doe"),
//                employees.stream().map(Employee::getName).collect(Collectors.toList()));

        assertThat(employees)
                .extracting(Employee::getName)
//                .containsAll(Arrays.asList("John Doe", "Jane Doe"));
        .isEqualTo(Arrays.asList("Jane Doe", "John Doe"));
    }

    @Test
    void test_update() {
        Employee employee = employeesDao.save("John Doe");
        long id = employee.getId();

        employeesDao.update(id, "John John Doe");

        Employee loaded = employeesDao.findById(id);
        assertEquals("John John Doe", loaded.getName());
    }

    @Test
    void test_add_address() {
        Employee employee = employeesDao.save("John Doe");
        employeesDao.addAddressTo(employee.getId(), new Address("Budapest"));
        employeesDao.addAddressTo(employee.getId(), new Address("Párizs"));

        Employee loaded = employeesDao.findEmployeeWithAddressesById(employee.getId());
        assertThat(loaded.getAddresses()).extracting(Address::getCity).containsExactlyInAnyOrder("Budapest", "Párizs");
    }

    @Test
    void test_upper() {
        Employee employee = employeesDao.save("John Doe");
        employeesDao.addAddressTo(employee.getId(), new Address("Budapest"));
        employeesDao.addAddressTo(employee.getId(), new Address("Zirc"));

        employeesDao.save("Jack Doe");

        employeesDao.updateEmployeesChangeNameToUppercase();

        List<Employee> employees = employeesDao.findAll();
        assertThat(employees).extracting(Employee::getName)
                .containsExactly("JACK DOE", "JOHN DOE");
    }

}
