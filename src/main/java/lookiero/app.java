package lookiero;

import lookiero.console.Console;

import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console();

        while (true) {
            String text = scanner.nextLine();
            console.process(text);
        }
    }
}
