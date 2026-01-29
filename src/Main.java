import generator.LogGenerator;
import model.Operation;
import model.Priority;
import model.Status;

import java.util.Date;


public class Main {
    public static void main(String[] args) {

        LogGenerator gen = LogGenerator.getOrCreate();
        gen.createLogEvent(new Date(), "user01", Operation.READ, Priority.INFO,
                "node01", "/api/test", Status.SUCCESS, 120);

    }
}
