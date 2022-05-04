package lookiero.console;

public class Console {
    public void process(String command) {
        System.out.println("Processing command: " + command);
        if (command.contains("->")) {
            postCommand(command);
        }
    }

    private void postCommand(String command) {
        String[] parts = command.split("->");
        if (parts[0].trim().length() < 1) {
            writeToConsole("No user specified");
            return;
        }
    }

    private void writeToConsole(String message) {
        System.out.println(message);
    }
}
