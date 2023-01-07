package pkt;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {

        String filePath = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int timer = 0;

            LinkedList<Process> input_queue = new LinkedList<Process>();
            LinkedList<Process> current_process = new LinkedList<Process>();
            LinkedList<Process> real_time_queue = new LinkedList<Process>();
            LinkedList<Process> user_job_queue = new LinkedList<Process>();
            LinkedList<Process> priority_one_queue = new LinkedList<Process>();
            LinkedList<Process> priority_two_queue = new LinkedList<Process>();
            LinkedList<Process> priority_three_queue =new LinkedList<Process>();
            LinkedList<Process> suspend_queue =new LinkedList<Process>();

            int id = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");

                int arrival_time = Integer.parseInt(parts[0]);
                int priority = Integer.parseInt(parts[1]);
                int process_time = Integer.parseInt(parts[2]);

                Process p = new Process(arrival_time, priority, process_time, id); //saniye ekle

                input_queue.add(p);
                id++;
            }
            while (!input_queue.isEmpty() || !real_time_queue.isEmpty() || !user_job_queue.isEmpty() || !priority_one_queue.isEmpty() || !priority_two_queue.isEmpty() || !priority_three_queue.isEmpty() || !current_process.isEmpty())
            {

                while (!input_queue.isEmpty() && input_queue.peek().arrival_time <= timer) {

                    if (input_queue.peek().priority == 0) {
                        real_time_queue.add(input_queue.peek()); //inputun ilk prosesini reale at
                        input_queue.remove(); //inputun ilk prosesini sil
                    }
                    else {
                        user_job_queue.add(input_queue.peek());
                        input_queue.remove();
                    }
                }

                while(!user_job_queue.isEmpty())
                {
                    switch(user_job_queue.peek().priority)
                    {
                        case 1:
                            priority_one_queue.add(user_job_queue.peek());
                            user_job_queue.remove();
                            break;
                        case 2:
                            priority_two_queue.add(user_job_queue.peek());
                            user_job_queue.remove();
                            break;
                        default:
                            priority_three_queue.add(user_job_queue.peek());
                            user_job_queue.remove();
                            break;
                    }
                }
                if(!current_process.isEmpty())
                {
                    if(--current_process.peek().process_time == 0)
                    {
                        current_process.peek().remove(timer);
                        current_process.remove();
                    }
                    else  //process bitmediyse
                    {
                        if((!input_queue.isEmpty() || !real_time_queue.isEmpty() || !user_job_queue.isEmpty() || !priority_one_queue.isEmpty() || !priority_two_queue.isEmpty() || !priority_three_queue.isEmpty()) && current_process.peek().priority != 0)
                        {
                            //prosesi ask�ya alma kodu yaz�lacak
                            if(++current_process.peek().priority > 3) //�ncelik d���rme
                            {
                                current_process.peek().priority = 3;
                            }
                            suspend_queue.add(current_process.peek());
                            current_process.peek().suspend(timer,current_process.peek().id);
                            switch (current_process.peek().priority) {
                                case 1:
                                    priority_one_queue.add(current_process.peek());
                                    break;
                                case 2:
                                    priority_two_queue.add(current_process.peek());
                                    break;
                                default:
                                    priority_three_queue.add(current_process.peek());
                                    break;
                            }
                            current_process.remove();
                        }
                    }
                }
                if((!real_time_queue.isEmpty() || !priority_one_queue.isEmpty() || !priority_two_queue.isEmpty() || !priority_three_queue.isEmpty()) && current_process.isEmpty())
                {
                    if(!real_time_queue.isEmpty()) {
                        current_process.add(real_time_queue.peek());
                        real_time_queue.remove();
                    }
                    else if (!priority_one_queue.isEmpty()) {
                        current_process.add(priority_one_queue.peek());
                        priority_one_queue.remove();
                    }
                    else if (!priority_two_queue.isEmpty()) {

                        current_process.add(priority_two_queue.peek());
                        priority_two_queue.remove();
                    }
                    else {
                        current_process.add(priority_three_queue.peek());
                        priority_three_queue.remove();
                    }
                }
                if(!current_process.isEmpty())
                    current_process.peek().run(timer);
                if(!suspend_queue.isEmpty() && (timer == suspend_queue.peek().suspend_time + 20)){
                    suspend_queue.peek().zaman_asimi(timer,suspend_queue.peek().id);
                    for (Iterator<Process> process = priority_two_queue.iterator(); process.hasNext(); ) {
                        Process process1 = process.next();
                        if (process1.id == suspend_queue.peek().id) {
                            process.remove();
                        }
                    }
                    for (Iterator<Process> process = priority_three_queue.iterator(); process.hasNext(); ) {
                        Process process1 = process.next();
                        if (process1.id == suspend_queue.peek().id) {
                            process.remove();
                        }
                    }
                    suspend_queue.remove();
                }
                timer++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}