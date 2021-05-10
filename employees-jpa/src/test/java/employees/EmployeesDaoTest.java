package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeesDaoTest {

    EmployeesDao employeesDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeesDao = new EmployeesDao(entityManagerFactory);
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

}
