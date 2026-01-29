package listener;

import event.ILogEvent;
import model.Operation;

import java.io.FileWriter;
import java.io.IOException;

public class OperationFilterListener implements ILogListener{
    private final Operation operation;
    private final String outputFile;

    public OperationFilterListener(Operation operation, String outputFile) {
        this.operation = operation;
        this.outputFile = outputFile;
    }

    @Override
    public void logAction(ILogEvent event) {
        if (event.getOperation() != operation){
            return;
        }

        try (FileWriter writer = new FileWriter(outputFile, true)){
            writer.write(event.toString() + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Failed to write operation log: " + e.getMessage());
        }
    }

}
