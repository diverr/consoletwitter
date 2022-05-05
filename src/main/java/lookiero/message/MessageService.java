package lookiero.message;

import java.util.List;

public interface MessageService {
    List<Message> getUserMessages(String userName);

    List<Message> getUserWall(String userName);

    void addMessage(String owner, String text);
}
