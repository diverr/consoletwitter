package lookiero.utils;

import lookiero.message.Message;

import java.time.Instant;

public class Parser {
    private final TimeProvider timeProvider;

    public Parser(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public String parseMessage(Message message) {
        return String.format("%s (%s)", message.getMessage(), prettyTime(message.getTime()));
    }

    public String parseWallMessage(Message message) {
        return String.format("%s - %s", message.getOwner(), parseMessage(message));
    }

    private String prettyTime(Instant time) {
        final int MINUTE = 60;
        final int HOUR = 60 * MINUTE;
        final int DAY = 24 * HOUR;

        long diff = timeProvider.now().getEpochSecond() - time.getEpochSecond();

        if (diff < MINUTE) {
            return diff + " second/s ago";
        } else if (diff < HOUR) {
            return diff / MINUTE + " minute/s ago";
        } else if (diff < DAY) {
            return diff / HOUR + " hour/s ago";
        } else {
            return diff / DAY + " day/s ago";
        }
    }
}
