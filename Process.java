import java.util.*;
import java.io.*;

public class Process {
    public int arrival_time;
    public int priority;
    public int process_time;
    public int id;
    public float saniye;

    public Process(int arrival_time, int priority, int process_time, int id, float saniye) {
        this.arrival_time = arrival_time;
        this.priority = priority;
        this.process_time= process_time;
        this.id = id;
        this.saniye = saniye;
    }
    public int getId() {
        return id;
    }
    public int getPriority() {
        return priority;
    }
    public int getTime() {
        return process_time;
    }

    public void run() {
        System.out.println("\033[32m"+ saniye + "sn proses başladı" + "       (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)" + "\033[0m");
        // İşlem süresi kadar bekle
        while (process_time > 0) {
            saniye += 1;
            if(priority >= 1 && )
            {
                
            }
            System.out.println("\033[33m"+ saniye + "sn proses yürütülüyor" + "   (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)" + "\033[0m");
            process_time -= 1;
        }
        System.out.println("\033[31m"+ ++saniye + "sn proses sonlandı" + "      (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)"+ "\033[0m");
    }
}
