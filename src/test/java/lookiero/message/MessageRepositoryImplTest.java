package lookiero.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MessageRepositoryImplTest {

    MessageRepositoryImpl messageRepository;

    @BeforeEach
    void setUp() {
        messageRepository = new MessageRepositoryImpl();
    }

    @Test
    @DisplayName("Should add and get messages")
    void addMessageTest() {
        messageRepository.addMessage(new Message("user1", "message"));
        messageRepository.addMessage(new Message("user1", "message"));
        messageRepository.addMessage(new Message("user2", "message"));
        messageRepository.addMessage(new Message("user3", "message"));

        List<Message> messagesUser1 = messageRepository.getMessagesByOwnerIn(List.of("user1"));
        List<Message> messagesUser2 = messageRepository.getMessagesByOwnerIn(List.of("user2"));
        List<Message> messagesUser3 = messageRepository.getMessagesByOwnerIn(List.of("user3"));

        assertEquals(2, messagesUser1.size());
        assertEquals(1, messagesUser2.size());
        assertEquals(1, messagesUser3.size());
    }

}
