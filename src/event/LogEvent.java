package event;

import java.util.Date;
import java.util.EventObject;


import model.Operation;
import model.Priority;
import model.Status;

/**
 * LogEvent represents a single log entry as an immutable event -
 * It represent past actions and should not be modified by listeners once created..
 */

public class LogEvent extends EventObject implements ILogEvent {
    private final Date date;
    private final String user;
    private final Operation operation;
    private final Priority priority;
    private final String node;
    private final String resource;
    private final Status status;
    private final int latency;

    public LogEvent(
            Object source,
            Date date,
            String user,
            Operation operation,
            Priority priority,
            String node,
            String resource,
            Status status,
            int latency
    ) {
        super(source);
        this.date = date;
        this.user = user;
        this.operation = operation;
        this.priority = priority;
        this.node = node;
        this.resource = resource;
        this.status = status;
        this.latency = latency;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public String getNode() {
        return node;
    }

    @Override
    public String getResource() {
        return resource;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public int getLatency() {
        return latency;
    }

    @Override
    public String toString() {
        return String.format(
                "[%1$tF %1$tT] %2$s user=%3$s operation=%4$s resource=%5$s status=%6$s latency=%7$dms node=%8$s",
                date, priority, user, operation, resource, status, latency, node
        );
    }
}
