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

    public Employee save(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = new Employee(name);
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
        return employee;
    }

    public List<Employee> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> employees = em.createQuery("select e from Employee e order by e.name", Employee.class).getResultList();
        em.close();
        return employees;
    }

//    public List<Employee> findAllWithNickNames() {
//        // TODO töltse be egy selecttel az összes alkalmazottat az összes nickkel
//    }

    public Employee findById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        em.close();
        return employee;
    }

    public Employee update(long id, String newName) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
        employee.setName(newName);
        em.getTransaction().commit();
        em.close();
        return employee;
    }

    public Employee update(UpdateEmployeeCommand command) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Employee employee = em.find(Employee.class, command.getId());
        employee.setName(command.getName()); // Jack Doe // John Doe // update set name = ''
                // Weaving buherál set ->

        em.getTransaction().commit();
        em.close();
        return employee;
    }

//    public void merge(Employee employee) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//        Employee managed = em.merge(employee);
//        // elmentésre kerül!
//        managed.setName("adsafds");
//
//        em.getTransaction().commit();
//        em.close();
//    }

    public void addAddressTo(long employeeId, Address address) {
        EntityManager em = entityManagerFactory.createEntityManager();
        System.out.println("*begin");
        em.getTransaction().begin();

        Employee employee = em.find(Employee.class, employeeId); // EHHEZ VAN EGY FELESLEGES SELECT
//        Employee employee = em.getReference(Employee.class, employeeId); // Proxy Employee obj. - CSAK id-ja van kitöltve
//
//        // Reference-re ráhívva betölti
//        //System.out.println("***" + employee.getName());
//
//        address.setEmployee(employee);
        employee.addAddress(address);
//
        //em.persist(address);

//        Employee employee = em.find(Employee.class, employeeId);
//        employee.addAddress(address);

        em.getTransaction().commit();
        System.out.println("*commit");
        em.close();
    }

    public Employee findEmployeeWithAddressesById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select distinct e from Employee e left join fetch e.addresses where e.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        finally {
            em.close();
        }
    }

    public void updateEmployeesChangeNameToUppercase() {
        EntityManager em = entityManagerFactory.createEntityManager();

        //entityManagerFactory.getCache().

        try {
//          Employee employee = findAll().get(0);
//            Employee employee = em.find(Employee.class, 1L);
//            System.out.println(employee.getAddresses().getClass().getName());
//            System.out.println(employee.getAddresses().toString());
//            System.out.println(employee.getName());

            em.getTransaction().begin();
            em.createNamedQuery("toUpper").executeUpdate();

//            employee = em.find(Employee.class, employee.getId());

            em.getTransaction().commit();

//            em.refresh(employee);
//            em.clear();

//            System.out.println(employee.getAddresses());

//            employee = em.find(Employee.class, 1L);
//            System.out.println(employee.getName());

        }
        finally {
            em.close();
        }
    }
}
