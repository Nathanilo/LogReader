package listener;

import event.ILogEvent;
import model.Operation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OperationFilterListener implements ILogListener{
    private final Operation operation;
    private final String outputFile;

    public OperationFilterListener(Operation operation, String outputFile) {
        this.operation = operation;

        //Check if output folder exists / create
        File folder = new File("output");
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Prepend folder to file name
        this.outputFile = "output/" + outputFile;
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
