package listener;

import event.ILogEvent;
import generator.ILogGenerator;

/**
 * Interface defining a log listener.
 * Each listener reacts to LogEvents created by the generator.
 */

public interface ILogListener {
    void logAction(ILogEvent logEvent);
    default void register(ILogGenerator generator){
        generator.register(this);
    };
    default void unregister(ILogGenerator generator) {
        generator.unregister(this);
    }
}
