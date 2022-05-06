package lookiero.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    @DisplayName("Should add and get users")
    void addUser() {
        userRepository.addUser(new User("user1", new ArrayList<>()));

        Optional<User> optUser1 = userRepository.findByName("user1");
        assertTrue(optUser1.isPresent());
        assertEquals("user1", optUser1.get().getName());
    }
}
