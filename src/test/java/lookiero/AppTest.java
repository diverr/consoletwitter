package lookiero;

import lookiero.message.MessageRepository;
import lookiero.message.MessageService;
import lookiero.user.UserRepository;
import lookiero.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppTest {

    @Mock
    OperationController operationController;

    @Mock
    MessageRepository messageRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    MessageService messageService;

    @Mock
    UserService userService;

    @Mock
    IO io;

    App app;

    @BeforeEach
    void setUp() {
        app = new App(messageRepository, userRepository, messageService, userService, io);
    }

    @Test
    @DisplayName("Should start the app")
    void init() {
        when(operationController.isRunning()).thenReturn(true).thenReturn(false);
        app.init(operationController);

        verify(operationController).wellcome();
        verify(operationController, times(2)).isRunning();
        verify(operationController, times(1)).listen();
    }
}
