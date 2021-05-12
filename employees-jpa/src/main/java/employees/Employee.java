package employees;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@NamedQuery(name = "toUpper", query = "update Employee e set e.name = upper(e.name)")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_name")
    private String name;

    @ElementCollection
    private Set<String> nickNames = new HashSet<>();

    @ElementCollection
    private Set<VacationEntry> vacationBookings = new HashSet<>();

    @ElementCollection
    private Map<String, String> phoneNumbers = new HashMap<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    //@OrderBy("city")
    @OrderColumn(name = "number")
    private List<Address> addresses = new ArrayList<>();

//    @Lob // 3MB TeX
//    @Basic(fetch = FetchType.LAZY) // Hibernate - nem működik
//    private String cv;

    public Employee(String name) {
        this.name = name;
    }

    public void addNickNames(String... names) {
        nickNames.addAll(Arrays.asList(names));
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setEmployee(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
