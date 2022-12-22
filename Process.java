import java.util.*;
import java.io.*;

public class Process {
    public int id;
    public int priority;
    public int time;
    public float saniye;

    public Process(float saniye,int id, int priority, int time) {
        this.id = id;
        this.priority = priority;
        this.time = time;
        this.saniye = saniye;
    }
    public int getId() {
        return id;
    }
    public int getPriority() {
        return priority;
    }
    public int getTime() {
        return time;
    }

    public void run() {
        System.out.println("\033[32m"+ saniye + "sn proses başladı" + "       (id:" + id + "  öncelik:" + priority + "  kalan süre:" + time + "sn)" + "\033[0m");
        // İşlem süresi kadar bekle
        while (time > 0) {
            saniye += 1;
            System.out.println("\033[33m"+ saniye + "sn proses yürütülüyor" + "   (id:" + id + "  öncelik:" + priority + "  kalan süre:" + time + "sn)" + "\033[0m");
            time -= 1;
        }
        System.out.println("\033[31m"+ ++saniye + "sn proses sonlandı" + "      (id:" + id + "  öncelik:" + priority + "  kalan süre:" + time + "sn)"+ "\033[0m");
      }
}
