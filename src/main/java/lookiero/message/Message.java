package lookiero.message;

import lookiero.utils.TimeProvider;

import java.time.Instant;

public class Message {
    private final String owner;
    private final String message;
    private final Instant time;

    public Message(String owner, String message, TimeProvider timeProvider) {
        this.owner = owner;
        this.message = message;
        this.time = timeProvider.now();
    }

    public String getOwner() {
        return owner;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTime() {
        return time;
    }
}
