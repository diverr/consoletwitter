package lookiero.message;

import lookiero.user.User;
import lookiero.user.UserRepository;
import lookiero.utils.TimeProvider;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final TimeProvider timeProvider;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, TimeProvider timeProvider) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.timeProvider = timeProvider;
    }

    @Override
    public List<Message> getUserMessages(String userName) {
        return messageRepository.getMessagesByOwnerIn(List.of(userName));
    }

    @Override
    public List<Message> getUserWall(String userName) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new RuntimeException(String.format("User %s not found", userName)));

        List<String> users = new ArrayList<>(user.getSubscriptions());
        users.add(user.getName());

        return messageRepository.getMessagesByOwnerIn(users);
    }

    @Override
    public void postMessage(String owner, String text) {
        User user = userRepository.findByName(owner).orElse(null);
        if (user == null) {
            userRepository.addUser(new User(owner, new ArrayList<>()));
        }

        Message message = new Message(owner, text, timeProvider);
        messageRepository.addMessage(message);
    }
}
