package employees;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/employees")
public class EmployeesResource {

    @Inject
    private EmployeesService employeesService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployeeDto save(CreateEmployeeCommand command) {
        return employeesService.save(command);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> findAll() {
        return employeesService.findAll();
    }
}
