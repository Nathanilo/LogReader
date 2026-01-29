package listener;

import event.ILogEvent;
import model.Operation;

import java.util.EnumMap;
import java.util.Map;

public class TrackerListener implements ILogListener {

    private final int interval;
    private int totalCount = 0;
    private final Map<Operation, Integer> operationCounts;

    public TrackerListener(int interval) {
        this.interval = interval;
        this.operationCounts = new EnumMap<>(Operation.class);

        for (Operation op : Operation.values()) {
            operationCounts.put(op, 0);
        }
    }

    @Override
    public void logAction(ILogEvent event) {
        totalCount++;
        Operation op = event.getOperation();
        operationCounts.put(op, operationCounts.get(op) + 1);

        if (totalCount % interval == 0) {
            printStats();
        }
    }

    private void printStats() {
        System.out.println("---- Log Statistics ----");
        System.out.println("Total events: " + totalCount);

        for (Map.Entry<Operation, Integer> entry : operationCounts.entrySet()) {
            double ratio = (double) entry.getValue() / totalCount * 100;
            System.out.printf(
                    "%s: %d (%.2f%%)%n",
                    entry.getKey(),
                    entry.getValue(),
                    ratio
            );
        }
        System.out.println("------------------------");
    }
}
