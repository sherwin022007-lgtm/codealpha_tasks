import java.io.FileWriter;

public class FileLogger {
    public static void log(String msg) {
        try (FileWriter fw = new FileWriter("hotel_logs.txt", true)) {
            fw.write(msg + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
