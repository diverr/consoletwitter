package lookiero.message;

import java.util.List;

public interface MessageRepository {
    void addMessage(Message message);

    List<Message> getMessagesByUser(String userName);
}
