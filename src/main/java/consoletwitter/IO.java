package consoletwitter;

public interface IO {
    void start();

    void stop();

    String readLine();

    void writeLine(String line);
}
