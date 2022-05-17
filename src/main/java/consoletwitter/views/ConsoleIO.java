package consoletwitter.views;

import consoletwitter.IO;

import java.util.Scanner;

public class ConsoleIO implements IO {
    Scanner scanner;

    public ConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        System.out.print("> ");
    }

    @Override
    public void stop() {
        scanner.close();
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
