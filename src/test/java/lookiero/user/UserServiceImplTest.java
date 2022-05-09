package lookiero.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Existing user1 should follow existing user2 that is not already followed by user1")
    void existingUser1FollowExistingUser2() {
        Optional<User> optUser1 = Optional.of(new User("user1", new ArrayList<>()));
        Optional<User> optUser2 = Optional.of(new User("user2", new ArrayList<>()));

        when(userRepository.findByName("user1")).thenReturn(optUser1);
        when(userRepository.findByName("user2")).thenReturn(optUser2);

        userService.followUser("user1", "user2");

        verify(userRepository).findByName("user1");
        verify(userRepository).findByName("user2");

        assertEquals(List.of("user2"), optUser1.get().getSubscriptions());
    }

    @Test
    @DisplayName("Existing user1 can not follow existing user2 that is already followed by user1")
    void existingUser1CantFollowExistingUser2AlreadyFollowed() {
        Optional<User> optUser1 = Optional.of(new User("user1", Arrays.asList("user2")));
        Optional<User> optUser2 = Optional.of(new User("user2", new ArrayList<>()));

        when(userRepository.findByName("user1")).thenReturn(optUser1);
        when(userRepository.findByName("user2")).thenReturn(optUser2);


        Exception result = assertThrows(RuntimeException.class, () -> userService.followUser("user1", "user2"));
        assertEquals("User user1 already follows user2", result.getMessage());

        verify(userRepository).findByName("user1");
        verify(userRepository).findByName("user2");
    }

    @Test
    @DisplayName("Non existing user1 can not follow existing user2")
    void notExistingUser1CantFollowExistingUser2() {
        when(userRepository.findByName("user1")).thenReturn(Optional.empty());

        Exception result = assertThrows(RuntimeException.class, () -> userService.followUser("user1", "user2"));
        assertEquals("User user1 not found", result.getMessage());

        verify(userRepository).findByName("user1");
        verify(userRepository, never()).findByName("user2");
    }

    @Test
    @DisplayName("Existing user1 can not follow non existing user2")
    void existingUser1CantFollowNonExistingUser2() {
        Optional<User> optUser1 = Optional.of(new User("user1", new ArrayList<>()));

        when(userRepository.findByName("user1")).thenReturn(optUser1);
        when(userRepository.findByName("user2")).thenReturn(Optional.empty());

        Exception result = assertThrows(RuntimeException.class, () -> userService.followUser("user1", "user2"));
        assertEquals("User user2 not found", result.getMessage());

        verify(userRepository).findByName("user1");
        verify(userRepository).findByName("user2");
    }
}
