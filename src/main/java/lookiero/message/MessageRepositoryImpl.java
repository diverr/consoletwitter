package lookiero.message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MessageRepositoryImpl implements MessageRepository {
    private final List<Message> messages = new ArrayList<>();

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> getMessagesByOwnerIn(List<String> owners) {
        return messages.stream()
                .filter(message -> owners.stream().anyMatch(message.getOwner()::equalsIgnoreCase))
                .sorted(Comparator.comparing(Message::getTime).reversed())
                .collect(Collectors.toList());
    }
}
