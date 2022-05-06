package lookiero;

import lookiero.message.MessageRepository;
import lookiero.message.MessageRepositoryImpl;
import lookiero.message.MessageService;
import lookiero.message.MessageServiceImpl;
import lookiero.user.UserRepository;
import lookiero.user.UserRepositoryImpl;
import lookiero.user.UserService;
import lookiero.user.UserServiceImpl;
import lookiero.views.ConsoleIO;

public class App {
    MessageRepository messageRepository;
    UserRepository userRepository;
    MessageService messageService;
    UserService userService;
    IO io;

    public App(MessageRepository messageRepository, UserRepository userRepository, MessageService messageService, UserService userService, IO io) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageService = messageService;
        this.userService = userService;
        this.io = io;
    }

    public static void main(String[] args) {
        MessageRepository messageRepository = new MessageRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        MessageService messageService = new MessageServiceImpl(messageRepository, userRepository);
        UserService userService = new UserServiceImpl(userRepository);

        IO io = new ConsoleIO();

        new App(messageRepository, userRepository, messageService, userService, io).init();
    }

    public void init() {
        OperationController operation = new OperationController(messageService, userService, io);

        operation.wellcome();

        while (operation.isRunning()) {
            operation.go();
        }

    }


}
