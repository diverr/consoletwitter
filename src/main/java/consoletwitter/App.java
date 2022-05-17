package consoletwitter;

import consoletwitter.message.MessageRepository;
import consoletwitter.message.MessageRepositoryImpl;
import consoletwitter.message.MessageService;
import consoletwitter.message.MessageServiceImpl;
import consoletwitter.user.UserRepository;
import consoletwitter.user.UserRepositoryImpl;
import consoletwitter.user.UserService;
import consoletwitter.user.UserServiceImpl;
import consoletwitter.utils.Parser;
import consoletwitter.utils.TimeProvider;
import consoletwitter.views.ConsoleIO;

import java.util.Scanner;

public class App {
    public App() {
    }

    public static void main(String[] args) {
        MessageRepository messageRepository = new MessageRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        TimeProvider timeProvider = new TimeProvider();

        MessageService messageService = new MessageServiceImpl(messageRepository, userRepository, timeProvider);
        UserService userService = new UserServiceImpl(userRepository);

        IO io = new ConsoleIO(new Scanner(System.in));

        Parser parser = new Parser(timeProvider);

        OperationController operation = new OperationController(messageService, userService, io, parser);

        new App().init(operation);
    }

    public void init(OperationController operation) {
        operation.welcome();

        while (operation.isRunning()) {
            operation.listen();
        }

        operation.bye();

    }


}
