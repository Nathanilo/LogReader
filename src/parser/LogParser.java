package parser;

import generator.LogGenerator;
import model.Operation;
import model.Priority;
import model.Status;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogParser {
    private final LogGenerator generator;
    private final String filePath;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public LogParser(String filePath, LogGenerator generator) {
        this.filePath = filePath;
        this.generator = generator;
    }

    public void parseAndGenerateEvents() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    generator.createLogEvent(parseLine(line));
                } catch (Exception e) {
                    System.err.println("Failed to parse line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read log file: " + e.getMessage());
        }
    }

    private Object[] parseLine(String line) throws Exception {

        // Split date and the rest
        int endDate = line.indexOf("]");
        String dateStr = line.substring(1, endDate);
        Date date = sdf.parse(dateStr);

        String rest = line.substring(endDate + 2); // skip "] "

        String[] parts = rest.split(" ");

        // Priority
        Priority priority = Priority.valueOf(parts[0]);

        // Fields user, operation, resource, status, latency
        String user = parts[1].split("=")[1];
        Operation operation = Operation.valueOf(parts[2].split("=")[1]);
        String resource = parts[3].split("=")[1];
        Status status = Status.valueOf(parts[4].split("=")[1]);

        // Latency: remove "ms" and parse integer
        int latency = Integer.parseInt(parts[5].split("=")[1].replace("ms", ""));

        // Node: extract from user id (optional, example node04 for user04)
        String node = "node" + user.substring(4);

        return new Object[]{date, user, operation, priority, node, resource, status, latency};
    }
}

