package event;
import model.Operation;
import model.Priority;

import java.util.Date;

/**
 * Interface defining the structure of a log event.
 */

public interface ILogEvent {
    Date getDate();
    String getUser();
    Operation getOperation();
    Priority getPriority();
    String getNode();
    String getResource();
    String getStatus();
    int getLatency();

}
