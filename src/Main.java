import generator.LogGenerator;
import listener.*;
import model.*;
import parser.LogParser;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        LogGenerator gen = LogGenerator.getOrCreate();

        //Operation Filters
        ILogListener readLogger = new OperationFilterListener(Operation.READ, "read.log");
        ILogListener writeLogger  = new OperationFilterListener(Operation.WRITE, "write.log");
        ILogListener updateLogger = new OperationFilterListener(Operation.UPDATE, "update.log");
        ILogListener deleteLogger = new OperationFilterListener(Operation.DELETE, "delete.log");

       // Filter logs by user e.g. filter all logs by user17
        ILogListener userLogger = new UserFilterListener("user17", "user.log");

        // Track operation happening every interval
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
