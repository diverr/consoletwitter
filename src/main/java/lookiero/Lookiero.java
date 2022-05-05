package lookiero;

import lookiero.message.MessageRepository;
import lookiero.message.MessageRepositoryImpl;
import lookiero.message.MessageService;
import lookiero.message.MessageServiceImpl;
import lookiero.user.UserRepository;
import lookiero.user.UserRepositoryImpl;
import lookiero.user.UserService;
import lookiero.user.UserServiceImpl;
import lookiero.views.ConsoleView;
import lookiero.views.View;

public class Lookiero {
    public static void main(String[] args) {

        MessageRepository messageRepository = new MessageRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        MessageService messageService = new MessageServiceImpl(messageRepository, userRepository);
        UserService userService = new UserServiceImpl(userRepository);

        View console = new ConsoleView(messageService, userService);

        while (true) {
            console.process();
        }
    }
}
