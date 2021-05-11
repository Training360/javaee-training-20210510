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

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    //@OrderBy("city")
    private List<Address> addresses = new ArrayList<>();

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
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
