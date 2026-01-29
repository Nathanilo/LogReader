package listener;

import event.ILogEvent;

/**
 * Interface defining a log listener.
 * Each listener reacts to LogEvents created by the generator.
 */

public interface ILogListener {
    void logAction(ILogEvent logEvent);
    void register();
    void unregister();
}
