package lookiero;

import lookiero.message.MessageService;
import lookiero.user.UserService;
import lookiero.utils.StringUtils;
import lookiero.views.IO;

public class OperationController {
    private final MessageService messageService;
    private final UserService userService;
    private final IO io;

    private final String STRING_POST = " -> ";
    private final String STRING_FOLLOWS = " follows ";
    private final String STRING_WALL = " wall ";

    public OperationController(MessageService messageService, UserService userService, IO io) {
        this.messageService = messageService;
        this.userService = userService;
        this.io = io;
    }

    public void run() {
        String text = io.readLine();

        // replace for regular expressions
        if (text.contains(STRING_POST)) {
            postCommand(text);
        } else if (text.contains(STRING_FOLLOWS)) {
            followCommand(text);
        } else if (text.contains(STRING_WALL)) {
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
        String userName = text.split(STRING_FOLLOWS)[0];
        String followUserName = text.split(STRING_FOLLOWS)[1];

        try {
            userService.followUser(userName, followUserName);
        } catch (Exception e) {
            io.writeLine(e.getMessage());
        }
    }

    private void postCommand(String text) {
        String userName = text.split(STRING_POST)[0];
        String message = text.split(STRING_POST)[1];

        try {
            messageService.postMessage(userName, message);
        } catch (Exception e) {
            io.writeLine(e.getMessage());
        }
    }

}
