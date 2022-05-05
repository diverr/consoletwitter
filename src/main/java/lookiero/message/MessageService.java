package lookiero.message;

import java.util.List;

public interface MessageService {
    List<Message> getUserMessages(String userName);

    List<Message> getUserWall(String userName);

    void postMessage(String owner, String text);
}
