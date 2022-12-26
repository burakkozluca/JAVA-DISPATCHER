import java.io.*;
import java.util.*;

public class java {
    
    static float saniye = 0;
    public static void main(String[] args) {

        String filePath = "giris.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            List<Process> queue1 = new LinkedList<>();
            List<Process> queue2 = new LinkedList<>();
            List<Process> queue3 = new LinkedList<>();
            List<Process> queue4 = new LinkedList<>();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");

                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);

                Process p = new Process(saniye, a, b, c);
                if(p.priority == 0)
                    queue1.add(p);
                else if(p.priority == 1)
                    queue2.add(p);
                else if(p.priority == 2)
                    queue3.add(p);
                else if(p.priority == 3)
                    queue4.add(p);
                FCFS fcfs = new FCFS(queue1);
                RoundRobin robin = new RoundRobin(queue4);
                fcfs.run();
                robin.run(); //round robin 
                }
            //System.out.println(queue1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

