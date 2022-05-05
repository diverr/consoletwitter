package lookiero.views;

import lookiero.message.Message;
import lookiero.message.MessageService;
import lookiero.user.UserService;

import java.util.Scanner;

public class ConsoleView implements View {
    private final Scanner scanner;
    private final MessageService messageService;
    private final UserService userService;

    public ConsoleView(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
        scanner = new Scanner(System.in);
    }

    public void process() {
        String text = scanner.nextLine();

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
            messageService.getUserMessages(text).forEach(message -> write(parseMessage(message)));
        } catch (Exception e) {
            write(e.getMessage());
        }
    }

    private void wallCommand(String text) {
        String userName = text.split(" ")[0];

        try {
            messageService.getUserWall(userName).forEach(message -> write(parseWallMessage(message)));
        } catch (Exception e) {
            write(e.getMessage());
        }
    }

    private void followCommand(String text) {
        String userName = text.split(" follows ")[0];
        String followUserName = text.split(" follows ")[1];

        try {
            userService.followUser(userName, followUserName);
        } catch (Exception e) {
            write(e.getMessage());
        }
    }

    private void postCommand(String text) {
        String userName = text.split(" -> ")[0];
        String message = text.split(" -> ")[1];

        try {
            messageService.postMessage(userName, message);
        } catch (Exception e) {
            write(e.getMessage());
        }
    }

    private void write(String message) {
        System.out.println(message);
    }

    private String parseMessage(Message message) {
        return String.format("%s (%s)", message.getMessage(), message.getTime());
    }

    private String parseWallMessage(Message message) {
        return String.format("%s: %s", message.getOwner(), parseMessage(message));
    }

}
