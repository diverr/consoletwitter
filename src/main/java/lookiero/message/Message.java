package lookiero.message;

import java.time.LocalDateTime;

public class Message {
    private final String owner;
    private final String message;
    private final LocalDateTime time;

    public Message(String owner, String message) {
        this.owner = owner;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public String getOwner() {
        return owner;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
