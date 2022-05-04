package lookiero.message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageRepositoryImpl implements MessageRepository {
    private final List<Message> messages = new ArrayList<>();

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> getMessagesByUser(String userName) {
        return messages.stream().filter(message -> message.getOwner().equals(userName)).collect(Collectors.toList());
    }
}
