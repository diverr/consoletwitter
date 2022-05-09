package lookiero;

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

    App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    @DisplayName("Should start the app")
    void init() {
        when(operationController.isRunning()).thenReturn(true).thenReturn(false);
        app.init(operationController);

        verify(operationController).welcome();
        verify(operationController, times(2)).isRunning();
        verify(operationController, times(1)).listen();
        verify(operationController).bye();
    }
}
