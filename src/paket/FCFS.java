package paket;
import java.util.*;
import java.io.*;

public class FCFS {
    // İşlemlerin listesi
    private LinkedList<Process> queue;

    public FCFS(LinkedList<Process> queue) {
        this.queue = (LinkedList<Process>) queue;
    }

    public void run(int sistem) {
        // İşlemlerin listesi döngüsü
        for (Process process : queue) {
            // İşlemin çalıştırılması
            process.run(sistem);
        }
    }
}