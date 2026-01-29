import generator.LogGenerator;
import listener.*;
import model.*;
import parser.LogParser;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        LogGenerator gen = LogGenerator.getOrCreate();

        ILogListener readLogger = new OperationFilterListener(Operation.READ, "read.log");
        ILogListener writeLogger  = new OperationFilterListener(Operation.WRITE, "write.log");
        ILogListener updateLogger = new OperationFilterListener(Operation.UPDATE, "update.log");
        ILogListener deleteLogger = new OperationFilterListener(Operation.DELETE, "delete.log");

        ILogListener userLogger = new UserFilterListener("user01", "user01.log");

        ILogListener tracker = new TrackerListener(3);

        readLogger.register(gen);
        writeLogger.register(gen);
        updateLogger.register(gen);
        deleteLogger.register(gen);

        userLogger.register(gen);

        tracker.register(gen);

        // Parse log file and generate events
        LogParser parser = new LogParser("logs/application.log", gen);
        parser.parseAndGenerateEvents();
    }
}
