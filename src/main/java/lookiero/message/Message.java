package lookiero.message;

import java.time.LocalDateTime;

public class Message {
    private final String owner;
    private final String message;
    private final String createdBy;
    private final LocalDateTime time;

    public Message(String owner, String message, String createdBy) {
        this.owner = owner;
        this.message = message;
        this.createdBy = createdBy;
        this.time = LocalDateTime.now();
    }

    public String getOwner() {
        return owner;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
