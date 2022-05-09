package lookiero.message;

import lookiero.user.User;
import lookiero.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    private final List<User> mockUsers = List.of(
            new User("user1", List.of("user2", "user3")),
            new User("user2", List.of()),
            new User("user3", List.of("user1"))
    );

    private final List<Message> mockMessages = List.of(
            new Message("user1", "message1"),
            new Message("user2", "message2"),
            new Message("user3", "message3")
    );

    @Mock
    MessageRepository messageRepository;

    @Mock
    UserRepository userRepository;

    MessageService messageService;

    @BeforeEach
    void setUp() {
        messageService = new MessageServiceImpl(messageRepository, userRepository);
    }

    @Test
    @DisplayName("Should return list with user messages")
    void getUserMessagesTest() {
        List<Message> mockedResult = mockMessages.stream().filter(message -> message.getOwner().equals("user1")).collect(Collectors.toList());

        when(messageRepository.getMessagesByOwnerIn(List.of("user1"))).thenReturn(mockedResult);

        List<Message> result = messageService.getUserMessages("user1");

        verify(messageRepository).getMessagesByOwnerIn(List.of("user1"));
        assertEquals(mockedResult, result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should return list with user wall messages")
    void getUserWallTest() {
        Optional<User> optMockedUser = mockUsers.stream()
                .filter(user -> user.getName().equals("user1"))
                .findFirst();

        when(userRepository.findByName("user1")).thenReturn(optMockedUser);
        when(messageRepository.getMessagesByOwnerIn(anyList())).thenReturn(mockMessages);

        List<Message> result = messageService.getUserWall("user1");

        verify(userRepository).findByName("user1");
        verify(messageRepository).getMessagesByOwnerIn(argThat(list -> list.contains("user1") && list.contains("user2") && list.contains("user3")));
        assertEquals(mockMessages, result);
    }

    @Test
    @DisplayName("Should post message of existing user")
    void postMessageExistingUserTest() {
        Optional<User> optMockedUser = mockUsers.stream()
                .filter(user -> user.getName().equals("user1"))
                .findFirst();

        when(userRepository.findByName("user1")).thenReturn(optMockedUser);

        messageService.postMessage("user1", "message");

        verify(userRepository, never()).addUser(any());
        verify(messageRepository).addMessage(argThat(message -> message.getOwner().equals("user1") && message.getMessage().equals("message")));
    }

    @Test
    @DisplayName("Should post message of new user")
    void postMessageNewUserTest() {
        when(userRepository.findByName("user1")).thenReturn(Optional.empty());

        messageService.postMessage("user1", "message");

        verify(userRepository).addUser(argThat(user -> user.getName().equals("user1") && user.getSubscriptions().isEmpty()));
        verify(messageRepository).addMessage(argThat(message -> message.getOwner().equals("user1") && message.getMessage().equals("message")));
    }
}
