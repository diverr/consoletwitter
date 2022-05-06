package lookiero.views;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleIOTest {
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Mock
    Scanner scanner;

    ConsoleIO consoleIO;

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        consoleIO = new ConsoleIO(scanner);
    }

    @Test
    @DisplayName("Should start console line")
    void start() {
        consoleIO.start();
        assertEquals("> ", outContent.toString());
    }

    @Test
    @DisplayName("Should print bye message and stop scanner")
    void stop() {
        consoleIO.stop();
        assertEquals("Bye!\n", outContent.toString());
        verify(scanner).close();
    }

    @Test
    @DisplayName("Should call scanner nextLine method")
    void readLine() {
        consoleIO.readLine();
        verify(scanner).nextLine();
    }

    @Test
    @DisplayName("Should print message")
    void writeLine() {
        consoleIO.writeLine("test");
        assertEquals("test\n", outContent.toString());
    }
}
