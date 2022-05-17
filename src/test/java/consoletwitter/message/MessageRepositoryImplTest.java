package consoletwitter.message;

import consoletwitter.utils.TimeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageRepositoryImplTest {
    @Mock
    TimeProvider timeProvider;

    MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        messageRepository = new MessageRepositoryImpl();
    }

    @Test
    @DisplayName("Should add and get messages")
    void addMessageTest() {
        when(timeProvider.now()).thenReturn(Instant.now().minus(1, ChronoUnit.MINUTES));

        messageRepository.addMessage(new Message("user1", "message", timeProvider));
        messageRepository.addMessage(new Message("user1", "message", timeProvider));
        messageRepository.addMessage(new Message("user2", "message", timeProvider));
        messageRepository.addMessage(new Message("user3", "message", timeProvider));

        List<Message> messagesUser1 = messageRepository.getMessagesByOwnerIn(List.of("user1"));
        List<Message> messagesUser2 = messageRepository.getMessagesByOwnerIn(List.of("user2"));
        List<Message> messagesUser3 = messageRepository.getMessagesByOwnerIn(List.of("user3"));

        assertEquals(2, messagesUser1.size());
        assertEquals(1, messagesUser2.size());
        assertEquals(1, messagesUser3.size());
    }

}
