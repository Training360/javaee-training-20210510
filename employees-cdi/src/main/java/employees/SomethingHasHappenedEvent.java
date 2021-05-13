package employees;

public class SomethingHasHappenedEvent {

    private String message;

    public SomethingHasHappenedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
