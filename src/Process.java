import java.io.*;
import java.util.*;
public class Process {
    public int arrival_time;
    public int priority;
    public int process_time;
    public int id;
    public int baslangic;
    public Process(int arrival_time, int priority, int process_time, int id) {
        this.arrival_time = arrival_time;
        this.priority = priority;
        this.process_time= process_time;
        this.id = id;
        this.baslangic = process_time;
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

    public void run(int saniye) {
        if(getTime() == baslangic) {
            System.out.println("\033[32m" + saniye + "sn proses başladı" + "       (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)" + "\033[0m");
        }
        else if(getTime() != 0)
        {
            System.out.println("\033[33m"+ saniye + "sn proses yürütülüyor" + "   (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)" + "\033[0m");
        }
            /* İşlem süresi kadar bekle
        while (process_time > 0) {
            if(priority >= 1 && arrival_time == saniye)
            {
                //System.out.println("\033[34m"+ saniye + "sn proses askıda" + "       (id:" + id + "  öncelik:" + ++priority + "  kalan süre:" + --process_time + "sn)" + "\033[0m");
                break;
            }
            //System.out.println("\033[33m"+ saniye + "sn proses yürütülüyor" + "   (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)" + "\033[0m");
            process_time -= 1;
        }
        *///System.out.println("\033[31m"+ ++saniye + "sn proses sonlandı" + "      (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)"+ "\033[0m");
    }
    public void remove(int saniye)
    {
        System.out.println("\033[31m"+ saniye + "sn proses sonlandı" + "      (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)"+ "\033[0m");
    }

    public void suspend(int saniye)
    {
        System.out.println("\033[34m"+ saniye + "sn proses askıda" + "        (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)" + "\033[0m");
    }
}
