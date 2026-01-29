package generator;


import listener.ILogListener;
import event.ILogEvent;
import model.Operation;
import model.Priority;
import model.Status;

import java.util.Date;
import java.util.List;

/**
 * Interface defining a log generator.
 * The generator creates log events and notifies registered listeners.
 */

public interface ILogGenerator {
    void register(ILogListener listener);
    void unregister(ILogListener listener);

    // Attribute-based (type-safe)
    void createLogEvent(
            Date date,
            String user,
            Operation operation,
            Priority priority,
            String node,
            String resource,
            Status status,
            int latency
    );

//    // List / Varargs-based (flexible)
//    void createLogEvent(Object... attributes);
}
