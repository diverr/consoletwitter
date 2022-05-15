package lookiero.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeUtils {

    private static final Integer MINUTE = 60;
    private static final Integer HOUR = 60 * MINUTE;
    private static final Integer DAY = 24 * HOUR;

    public static String getRelativeTime(LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();
        long diff = now.toEpochSecond(ZoneOffset.UTC) - time.toEpochSecond(ZoneOffset.UTC);

        if (diff < MINUTE) {
            return diff + " seconds ago";
        } else if (diff < HOUR) {
            return diff / MINUTE + " minutes ago";
        } else if (diff < DAY) {
            return diff / HOUR + " hours ago";
        } else {
            return diff / DAY + " days ago";
        }
    }
}
