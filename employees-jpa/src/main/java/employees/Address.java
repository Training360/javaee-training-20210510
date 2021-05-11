package employees;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

//    @Column(name = "emp_order")
//    private int order;

    @ManyToOne
    private Employee employee;

    public Address(String city) {
        this.city = city;
    }
}
