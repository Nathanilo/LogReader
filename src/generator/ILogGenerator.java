package generator;


import listener.ILogListener;
import event.ILogEvent;
import java.util.List;

/**
 * Interface defining a log generator.
 * The generator creates log events and notifies registered listeners.
 */

public interface ILogGenerator {
    void register(ILogListener listener);
    void unregister(ILogListener listener);
    void createLogEvent(Object... attributes);
}
