package lookiero.views;

import lookiero.IO;

import java.util.Scanner;

public class ConsoleIO implements IO {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        System.out.print("> ");
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
