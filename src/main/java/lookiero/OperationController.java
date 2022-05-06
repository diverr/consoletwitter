package lookiero;

import lookiero.message.MessageService;
import lookiero.user.UserService;
import lookiero.utils.StringUtils;

import java.util.regex.Pattern;

public class OperationController {
    private final MessageService messageService;
    private final UserService userService;
    private final IO io;

    private final String STRING_COMMAND_POST = " -> ";
    private final String STRING_COMMAND_FOLLOWS = " follows ";
    private final String STRING_COMMAND_WALL = " wall";
    private final String STRING_COMMAND_QUIT = "q";

    private boolean running;

    public OperationController(MessageService messageService, UserService userService, IO io) {
        this.messageService = messageService;
        this.userService = userService;
        this.io = io;

        running = true;
    }

    public void go() {
        io.start();
        String text = io.readLine();

        if (isPostCommand(text)) {
            postCommand(text);
        } else if (isFollowCommand(text)) {
            followCommand(text);
        } else if (isWallCommand(text)) {
            wallCommand(text);
        } else if (text.equalsIgnoreCase(STRING_COMMAND_QUIT)) {
            exitCommand();
        } else {
            readCommand(text);
        }
    }

    private boolean isPostCommand(String text) {
        Pattern pattern = Pattern.compile("(.*)" + STRING_COMMAND_POST + "(.*)", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(text).matches();
    }

    private boolean isFollowCommand(String text) {
        Pattern pattern = Pattern.compile("(.*)" + STRING_COMMAND_FOLLOWS + "(.*)", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(text).matches();
    }

    private boolean isWallCommand(String text) {
        Pattern pattern = Pattern.compile("(.*)" + STRING_COMMAND_WALL, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(text).matches();
    }

    private void exitCommand() {
        io.stop();
        running = false;
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
        String userName = text.split(STRING_COMMAND_FOLLOWS)[0];
        String followUserName = text.split(STRING_COMMAND_FOLLOWS)[1];

        try {
            userService.followUser(userName, followUserName);
            io.writeLine(String.format("%s is now following %s", userName, followUserName));
        } catch (Exception e) {
            io.writeLine(e.getMessage());
        }
    }

    private void postCommand(String text) {
        String userName = text.split(STRING_COMMAND_POST)[0];
        String message = text.split(STRING_COMMAND_POST)[1];

        try {
            messageService.postMessage(userName, message);
        } catch (Exception e) {
            io.writeLine(e.getMessage());
        }
    }

    public void wellcome() {
        io.writeLine("Welcome to Lookiero!");
    }

    public boolean isRunning() {
        return running;
    }
}
