package listener;

import event.ILogEvent;

import java.io.FileWriter;
import java.io.IOException;

public class UserFilterListener implements ILogListener {

    private final String user;
    private final String outputFile;

    public UserFilterListener(String user, String outputFile) {
        this.user = user;
        this.outputFile = outputFile;
    }

    @Override
    public void logAction(ILogEvent event) {
        if (!event.getUser().equals(user)) {
            return;
        }

        try (FileWriter writer = new FileWriter(outputFile, true)) {
            writer.write(event.toString() + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Failed to write user log: " + e.getMessage());
        }
    }
}
