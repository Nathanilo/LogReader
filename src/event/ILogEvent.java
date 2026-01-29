package event;
import model.Operation;
import model.Priority;
import model.Status;

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
    Status getStatus();
    int getLatency();

}
