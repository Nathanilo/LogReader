package generator;

import listener.ILogListener;

import java.util.ArrayList;
import java.util.List;

import event.LogEvent;
import java.util.Date;
import model.Operation;
import model.Priority;
import model.Status;


public class LogGenerator implements ILogGenerator {
    private static LogGenerator INSTANCE = null;

    private final List<ILogListener> listeners;

    private LogGenerator(){
        this.listeners = new ArrayList<>();
    }

    public static LogGenerator getOrCreate() {
        if (INSTANCE == null) {
            INSTANCE = new LogGenerator();
        }
        return  INSTANCE;
    }

    @Override
    public void register(ILogListener listener){
        if(!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    @Override
    public void unregister(ILogListener listener){
        listeners.remove(listener);
    }

    @Override
    public void createLogEvent(
            Date date,
            String user,
            Operation operation,
            Priority priority,
            String node,
            String resource,
            Status status,
            int latency
    ) {
        LogEvent event = new LogEvent(
                this,
                date,
                user,
                operation,
                priority,
                node,
                resource,
                status,
                latency
        );

        for (ILogListener listener : listeners) {
            listener.logAction(event);
        }
    }

    @Override
    public void createLogEvent(Object... attributes) {

        if (attributes == null || attributes.length != 8) {
            throw new IllegalArgumentException(
                    "Expected attributes: Date, User, Operation, Priority, Node, Resource, Status, Latency"
            );
        }

        try {
            createLogEvent(
                    (java.util.Date) attributes[0],
                    (String) attributes[1],
                    (model.Operation) attributes[2],
                    (model.Priority) attributes[3],
                    (String) attributes[4],
                    (String) attributes[5],
                    (model.Status) attributes[6],
                    (int) attributes[7]
            );
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
                    "Invalid attribute types provided",
                    e
            );
        }
    }
}
