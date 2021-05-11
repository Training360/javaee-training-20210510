package employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class VacationEntry {

    private LocalDate startDate;

    private int daysTaken;
}
