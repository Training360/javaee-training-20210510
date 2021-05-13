package employees;

import lombok.Data;

@Data
public class TransferCommand {

    private long sourceId;

    private long destinationId;

    private int diff;
}
