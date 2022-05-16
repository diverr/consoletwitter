package lookiero.utils;

import lookiero.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParserTest {

    @Mock
    TimeProvider timeProvider;

    Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser(timeProvider);
    }

    @Test
    @DisplayName("Should parse message")
    void parseMessage() {
        when(timeProvider.now()).thenReturn(Instant.now());
        Message message = new Message("user1", "message", timeProvider);

        when(timeProvider.now()).thenReturn(Instant.now().plus(5, ChronoUnit.MINUTES));

        String result = parser.parseMessage(message);
        assertEquals("message (5 minute/s ago)", result);
    }

    @Test
    @DisplayName("Should parse wall message")
    void parseWallMessage() {
        when(timeProvider.now()).thenReturn(Instant.now());
        Message message = new Message("user1", "message", timeProvider);

        when(timeProvider.now()).thenReturn(Instant.now().plus(5, ChronoUnit.MINUTES));

        String result = parser.parseWallMessage(message);
        assertEquals("user1 - message (5 minute/s ago)", result);
    }

    @Test
    @DisplayName("Should parse messages with correct parsed times")
    void parseMessageWithCorrectTimes() {
        Instant now = Instant.now();

        when(timeProvider.now()).thenReturn(now);
        Message message = new Message("user1", "message", timeProvider);

        when(timeProvider.now()).thenReturn(now.plus(59, ChronoUnit.SECONDS));
        String result1 = parser.parseMessage(message);
        assertEquals("message (59 seconds ago)", result1);

        when(timeProvider.now()).thenReturn(now.plus(60, ChronoUnit.SECONDS));
        String result2 = parser.parseMessage(message);
        assertEquals("message (1 minute ago)", result2);

        when(timeProvider.now()).thenReturn(now.plus(24, ChronoUnit.MINUTES));
        String result3 = parser.parseMessage(message);
        assertEquals("message (24 minutes ago)", result3);

        when(timeProvider.now()).thenReturn(now.plus(60, ChronoUnit.MINUTES));
        String result4 = parser.parseMessage(message);
        assertEquals("message (1 hour ago)", result4);

        when(timeProvider.now()).thenReturn(now.plus(23, ChronoUnit.HOURS));
        String result5 = parser.parseMessage(message);
        assertEquals("message (23 hours ago)", result5);

        when(timeProvider.now()).thenReturn(now.plus(25, ChronoUnit.HOURS));
        String result6 = parser.parseMessage(message);
        assertEquals("message (1 day ago)", result6);

        when(timeProvider.now()).thenReturn(now.plus(28, ChronoUnit.DAYS));
        String result7 = parser.parseMessage(message);
        assertEquals("message (28 days ago)", result7);
    }
}
