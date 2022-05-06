package lookiero.utils;

import lookiero.message.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

    @Test
    @DisplayName("Should parse message")
    void parseMessage() {
        String result = StringUtils.parseMessage(new Message("user1", "message"));
        assertEquals("message (moments ago)", result);
    }

    @Test
    @DisplayName("Should parse wall message")
    void parseWallMessage() {
        String result = StringUtils.parseWallMessage(new Message("user1", "message"));
        assertEquals("user1 - message (moments ago)", result);
    }

    @Test
    @DisplayName("Should transform time to pretty time")
    void prettyTime() {
        String result = StringUtils.prettyTime(LocalDateTime.now());
        assertEquals("moments ago", result);
    }
}
