package lookiero.utils;

import lookiero.message.Message;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.util.Locale;

public class StringUtils {
    public static String parseMessage(Message message) {
        return String.format("%s (%s)", message.getMessage(), prettyTime(message.getTime()));
    }

    public static String parseWallMessage(Message message) {
        return String.format("%s - %s", message.getOwner(), parseMessage(message));
    }

    public static String prettyTime(LocalDateTime time) {
        return new PrettyTime(new Locale("en")).format(time);
    }
}
