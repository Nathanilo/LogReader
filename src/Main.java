import generator.LogGenerator;
import listener.*;
import model.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        LogGenerator gen = LogGenerator.getOrCreate();

        ILogListener readLogger =
                new OperationFilterListener(Operation.READ, "read.log");

        ILogListener userLogger =
                new UserFilterListener("user01", "user01.log");

        ILogListener tracker =
                new TrackerListener(3);

        readLogger.register(gen);
        userLogger.register(gen);
        tracker.register(gen);

        gen.createLogEvent(new Date(), "user01", Operation.READ,
                Priority.INFO, "node01", "/api/test", Status.SUCCESS, 120);

        gen.createLogEvent(new Date(), "user02", Operation.WRITE,
                Priority.ERROR, "node02", "/data/output.json", Status.ERROR, 45);

        gen.createLogEvent(new Date(), "user01", Operation.READ,
                Priority.INFO, "node01", "/api/test", Status.SUCCESS, 90);
    }
}
