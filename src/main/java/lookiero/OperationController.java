package lookiero;

import lookiero.message.MessageService;
import lookiero.user.UserService;
import lookiero.utils.StringUtils;
import lookiero.views.IO;

public class OperationController {
    private final MessageService messageService;
    private final UserService userService;
    private final IO io;

    public OperationController(MessageService messageService, UserService userService, IO io) {
        this.messageService = messageService;
        this.userService = userService;
        this.io = io;
    }

    public void run() {
        String text = io.readLine();

        // replace for regular expressions
        if (text.contains(" -> ")) {
            postCommand(text);
        } else if (text.contains(" follows ")) {
            followCommand(text);
        } else if (text.contains(" wall")) {
            wallCommand(text);
        } else {
            readCommand(text);
        }
    }

    private void readCommand(String text) {
        try {
            messageService.getUserMessages(text).forEach(message -> io.writeLine(StringUtils.parseMessage(message)));
        } catch (Exception e) {
            io.writeLine(e.getMessage());
        }
    }

    private void wallCommand(String text) {
        String userName = text.split(" ")[0];

        try {
            messageService.getUserWall(userName).forEach(message -> io.writeLine(StringUtils.parseWallMessage(message)));
        } catch (Exception e) {
            io.writeLine(e.getMessage());
        }
    }

    private void followCommand(String text) {
        String userName = text.split(" follows ")[0];
        String followUserName = text.split(" follows ")[1];

        try {
            userService.followUser(userName, followUserName);
        } catch (Exception e) {
            io.writeLine(e.getMessage());
        }
    }

    private void postCommand(String text) {
        String userName = text.split(" -> ")[0];
        String message = text.split(" -> ")[1];

        try {
            messageService.postMessage(userName, message);
        } catch (Exception e) {
            io.writeLine(e.getMessage());
        }
    }

}
