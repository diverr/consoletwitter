package lookiero.message;

import lookiero.user.User;
import lookiero.user.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> getUserMessages(String userName) {
        return messageRepository.getMessagesByUser(userName);
    }

    @Override
    public List<Message> getUserWall(String userName) {
        User user = userRepository.getUserByName(userName).orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<String> users = user.getSubscriptions();
        users.add(user.getName());

        return users.stream().map(messageRepository::getMessagesByUser)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Message::getTime))
                .collect(Collectors.toList());
    }

    @Override
    public void addMessage(Message message) {
        User user = userRepository.getUserByName(message.getOwner()).orElse(null);
        if (user == null) {
            userRepository.addUser(new User(message.getOwner(), new ArrayList<>()));
        }
        messageRepository.addMessage(message);
    }
}
