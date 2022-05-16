package lookiero;

import lookiero.message.MessageRepository;
import lookiero.message.MessageRepositoryImpl;
import lookiero.message.MessageService;
import lookiero.message.MessageServiceImpl;
import lookiero.user.UserRepository;
import lookiero.user.UserRepositoryImpl;
import lookiero.user.UserService;
import lookiero.user.UserServiceImpl;
import lookiero.utils.Parser;
import lookiero.utils.TimeProvider;
import lookiero.views.ConsoleIO;

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
