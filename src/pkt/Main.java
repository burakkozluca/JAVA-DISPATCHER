package pkt;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int timer = 0; //Sistem saniyesi
            //Kuyruklarimiz olusturuldu.
            LinkedList<Process> input_queue = new LinkedList<Process>();
            LinkedList<Process> current_process = new LinkedList<Process>();
            LinkedList<Process> real_time_queue = new LinkedList<Process>();
            LinkedList<Process> user_job_queue = new LinkedList<Process>();
            LinkedList<Process> priority_one_queue = new LinkedList<Process>();
            LinkedList<Process> priority_two_queue = new LinkedList<Process>();
            LinkedList<Process> priority_three_queue = new LinkedList<Process>();
            LinkedList<Process> suspend_queue = new LinkedList<Process>();

            int id = 0; //Proses id degiskeni olusturuldu.
            while ((line = br.readLine()) != null) //Tüm giris.txt satir satir okundu.
            {
                String[] parts = line.split(", "); //Her satirdaki veriler , ile ayrildi.
                int arrival_time = Integer.parseInt(parts[0]); //İlgili argumanlar ilgili degiskenlere atandi.
                int priority = Integer.parseInt(parts[1]);
                int process_time = Integer.parseInt(parts[2]);

                Process p = new Process(arrival_time, priority, process_time, id); //Proses olusturuldu

                input_queue.add(p); //olusturulan proses input_queue'ya eklendi.
                id++;
            }
            //tum kuyruklar bos olana kadar sistemin calismasini saglayan while
            while (!input_queue.isEmpty() || !real_time_queue.isEmpty() || !user_job_queue.isEmpty() || !priority_one_queue.isEmpty() || !priority_two_queue.isEmpty() || !priority_three_queue.isEmpty() || !current_process.isEmpty()) {
                //input_queue bossa ve input_queue'nun ilk elemaninin varis zamani sistem saniyesine esit veya kucukse proses ekleme islemi yapilacak
                while (!input_queue.isEmpty() && input_queue.peek().arrival_time <= timer) {
                    if (input_queue.peek().priority == 0) //oncelik 0'sa prosesi real_time_queue'ya ekle
                    {
                        real_time_queue.add(input_queue.peek()); //input_queue'nun ilk prosesini ekle
                        input_queue.remove(); //eklenen prosesi input_queue'dan sil
                    } else // diger oncelikleri user_job_queue'ya ekle
                    {
                        user_job_queue.add(input_queue.peek()); //input_queue'nun ilk prosesini ekle
                        input_queue.remove(); //eklenen prosesi input_queue'dan sil
                    }
                }
                //user_job_queue bos olana kadar devam eden while
                while (!user_job_queue.isEmpty()) {
                    //user_job_queue'daki prosesler onceliklerine gore ilgili kuyruklara eklenir.
                    switch (user_job_queue.peek().priority) {
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
                //Calisan bir proses varsa
                if (!current_process.isEmpty()) {
                    //process_time 1 azaltilir ve 0 olursa proses silinir.
                    if (--current_process.peek().process_time == 0) {
                        current_process.peek().remove(timer);
                        current_process.remove();
                    }
                    else  //proses bitmediyse
                    {
                        //Kuyruklar bos degilse ve curren_process'in onceligi 0'dan farkliysa
                        if ((!input_queue.isEmpty() || !real_time_queue.isEmpty() || !user_job_queue.isEmpty() || !priority_one_queue.isEmpty() || !priority_two_queue.isEmpty() || !priority_three_queue.isEmpty()) && current_process.peek().priority != 0) {
                            //oncelik arttirma ve onceligi 4 olursa oncelik tekrardan 3 yapilir.
                            if (++current_process.peek().priority > 3)
                            {
                                current_process.peek().priority = 3;
                            }
                            suspend_queue.add(current_process.peek()); //Prosesi askiya alma
                            current_process.peek().suspend(timer, current_process.peek().id);
                            //onceligi degisen prosesi yeni kuyruguna ekleme
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
                //Tum kuyruklardan (current_process haric) biri bos degilse ve current_process bossa
                if ((!real_time_queue.isEmpty() || !priority_one_queue.isEmpty() || !priority_two_queue.isEmpty() || !priority_three_queue.isEmpty()) && current_process.isEmpty()) {
                    //ilgili kuyruk bos degilse current_process'e eklenir.
                    if (!real_time_queue.isEmpty()) {
                        current_process.add(real_time_queue.peek());
                        real_time_queue.remove();
                    } else if (!priority_one_queue.isEmpty()) {
                        current_process.add(priority_one_queue.peek());
                        priority_one_queue.remove();
                    } else if (!priority_two_queue.isEmpty()) {
                        current_process.add(priority_two_queue.peek());
                        priority_two_queue.remove();
                    } else {
                        current_process.add(priority_three_queue.peek());
                        priority_three_queue.remove();
                    }
                }
                if (!current_process.isEmpty())
                    current_process.peek().run(timer); //Proses calistirilir.
                //aski kuyrugu bos degilse ve prosesin askiya alinma zamanindan 20 saniye gecmisse
                if (!suspend_queue.isEmpty() && (timer == suspend_queue.peek().suspend_time + 20)) {
                    suspend_queue.peek().zaman_asimi(timer, suspend_queue.peek().id); //proses zaman asimina ugrar.
                    //zaman asimina ugrayan proses priority_two_queue'dan silinir.
                    for (Iterator<Process> process = priority_two_queue.iterator(); process.hasNext(); ) {
                        Process process1 = process.next();
                        if (process1.id == suspend_queue.peek().id) {
                            process.remove();
                        }
                    }
                    //zaman asimina ugrayan proses priority_three_queue'dan silinir.
                    for (Iterator<Process> process = priority_three_queue.iterator(); process.hasNext(); ) {
                        Process process1 = process.next();
                        if (process1.id == suspend_queue.peek().id) {
                            process.remove();
                        }
                    }
                    suspend_queue.remove(); //askidaki proses silinir.
                }
                timer++; //sistem saniyesi arttirilir.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}