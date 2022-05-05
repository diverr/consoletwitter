package lookiero.views;

import java.util.Scanner;

public class ConsoleIO implements IO {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
