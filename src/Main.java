import java.util.*;
import java.io.*;
public class Main {
    static float timer = 0;
    public static void main(String[] args) {

        String filePath = "giris.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            Queue input_queue = new Queue();
            Queue real_time_queue = new Queue();
            Queue user_job_queue = new Queue();
            Queue priority_one_queue = new Queue();
            Queue priority_two_queue = new Queue();
            Queue priority_three_queue = new Queue();

            Queue current_process = new Queue();

            int id = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");

                int arrival_time = Integer.parseInt(parts[0]);
                int priority = Integer.parseInt(parts[1]);
                int process_time = Integer.parseInt(parts[2]);

                Process p = new Process(arrival_time, priority, process_time, id, timer); //saniye ekle

                input_queue.enqueue(p);
                id++;
                // if(p.priority == 0)
                //     queue1.add(p);
                // else if(p.priority == 1)
                //     queue2.add(p);
                // else if(p.priority == 2)
                //     queue3.add(p);
                // else if(p.priority == 3)
                //     queue4.add(p);
                // FCFS fcfs = new FCFS(queue1);
                // RoundRobin robin = new RoundRobin(queue4);
                // fcfs.run();
                // robin.run();
            }
            while (!input_queue.isEmpty() || !real_time_queue.isEmpty() || !user_job_queue.isEmpty() || !priority_one_queue.isEmpty() || !priority_two_queue.isEmpty() || !priority_three_queue.isEmpty() || !current_process.isEmpty())
            {
                if(!current_process.isEmpty())
                {
                    if(--current_process.peek().process_time == 0)
                    {
                        current_process.dequeue(); //güncel processin çalışması sonlandı.
                        //ekran çıktısı yazdırılabilir.
                    }
                    else //process bitmediyse
                    {
                        if((!real_time_queue.isEmpty() || !user_job_queue.isEmpty() || !priority_one_queue.isEmpty() || !priority_two_queue.isEmpty() || !priority_three_queue.isEmpty()) && current_process.peek().priority != 0)
                        {
                            //prosesi askıya alma kodu yazılacak
                            if(++current_process.peek().priority > 3) //öncelik düşürme
                            {
                                current_process.peek().priority = 3;
                            }

                            switch (current_process.peek().priority) {
                                case 1:
                                    priority_one_queue.enqueue(current_process.peek());
                                    break;
                                case 2:
                                    priority_two_queue.enqueue(current_process.peek());
                                    break;
                                default:
                                    priority_three_queue.enqueue(current_process.peek());
                                    break;
                            }
                            current_process.dequeue();
                        }
                    }
                }
                while (!input_queue.isEmpty() && input_queue.peek().arrival_time <= timer) {

                    if (input_queue.peek().priority == 0) {
                        real_time_queue.enqueue(input_queue.peek());
                        input_queue.dequeue();
                        real_time_queue.peek().fcfsrun();
                    }
                    else {
                        user_job_queue.enqueue(input_queue.peek());
                        input_queue.dequeue();
                    }
                }

                while(!user_job_queue.isEmpty())
                {
                    switch(user_job_queue.peek().priority)
                    {
                        case 1:
                            priority_one_queue.enqueue(user_job_queue.peek());
                            user_job_queue.dequeue();
                            priority_one_queue.peek().run(input_queue.peek().arrival_time);
                            break;
                        case 2:
                            priority_two_queue.enqueue(user_job_queue.peek());
                            user_job_queue.dequeue();
                            priority_one_queue.peek().run(input_queue.peek().arrival_time);
                            break;
                        default:
                            priority_three_queue.enqueue(user_job_queue.peek());
                            user_job_queue.dequeue();
                            priority_one_queue.peek().run(input_queue.peek().arrival_time);
                            break;
                    }
                }
                timer++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}