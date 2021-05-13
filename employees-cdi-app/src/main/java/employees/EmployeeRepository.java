package employees;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends EntityRepository<Employee, Long> {

    @Query("select e from Employee e order by e.name")
    List<Employee> findAllOrderByName();
}
