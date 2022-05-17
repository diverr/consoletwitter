package consoletwitter;

import consoletwitter.message.MessageService;
import consoletwitter.user.UserService;
import consoletwitter.utils.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationControllerTest {

    @Mock
    MessageService messageService;

    @Mock
    UserService userService;

    @Mock
    IO io;

    @Mock
    Parser parser;

    OperationController operationController;

    @BeforeEach
    void setUp() {
        operationController = new OperationController(messageService, userService, io, parser);
    }

    @Test
    @DisplayName("Should call post command")
    void listenPost() {
        when(io.readLine()).thenReturn("User1 -> This is a test");

        operationController.listen();

        verify(messageService).postMessage("User1", "This is a test");
    }

    @Test
    @DisplayName("Should call follow command")
    void listenFollow() {
        when(io.readLine()).thenReturn("User1 follows User2");

        operationController.listen();

        verify(userService).followUser("User1", "User2");
    }

    @Test
    @DisplayName("Should call wall command")
    void listenWall() {
        when(io.readLine()).thenReturn("User1 wall");

        operationController.listen();

        verify(messageService).getUserWall("User1");
    }

    @Test
    @DisplayName("Should call read command")
    void listenRead() {
        when(io.readLine()).thenReturn("User1");

        operationController.listen();

        verify(messageService).getUserMessages("User1");
    }

    @Test
    @DisplayName("Should call quit command")
    void listenQuit() {
        when(io.readLine()).thenReturn("q");

        operationController.listen();

        verify(io).stop();
    }

    @Test
    @DisplayName("Should write welcome message")
    void welcome() {
        operationController.welcome();
        verify(io).writeLine("Welcome to ConsoleTwitter! (type 'q' to quit)");
    }

}
