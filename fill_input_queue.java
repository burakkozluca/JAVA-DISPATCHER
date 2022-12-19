import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class fill_input_queue {
    public static void readAndQueueProcesses(String filePath, Queue<Process> inputQueue) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                Process process = parseProcess(line);

                inputQueue.add(process);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Process parseProcess(String line) {

        String[] values = line.split(",");
        String id = values[0];
        int arrivalTime = Integer.parseInt(values[1]);
        int burstTime = Integer.parseInt(values[2]);

        return new Process(id, arrivalTime, burstTime);
    }
}