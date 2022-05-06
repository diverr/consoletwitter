package lookiero.message;

import lookiero.user.User;
import lookiero.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> getUserMessages(String userName) {
        return messageRepository.getMessagesByOwnerIn(List.of(userName));
    }

    @Override
    public List<Message> getUserWall(String userName) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new IllegalArgumentException("User not found"));

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

        Message message = new Message(owner, text);
        messageRepository.addMessage(message);
    }
}
