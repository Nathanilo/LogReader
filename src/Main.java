import generator.LogGenerator;
import model.Operation;
import model.Priority;
import model.Status;

import java.util.Date;


public class Main {
    public static void main(String[] args) {

        LogGenerator gen = LogGenerator.getOrCreate();

        // Typed factory
        gen.createLogEvent(
                new Date(),
                "user01",
                Operation.READ,
                Priority.INFO,
                "node01",
                "/api/test",
                Status.SUCCESS,
                120
        );

        // Varargs factory
        gen.createLogEvent(
                new Date(),
                "user02",
                Operation.WRITE,
                Priority.ERROR,
                "node02",
                "/data/output.json",
                Status.ERROR,
                45
        );
    }
}
