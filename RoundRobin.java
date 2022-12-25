import java.util.*;
import java.io.*;

public class RoundRobin {
    // Proseslerin kuyruğu
    private List<Process> queue4;
    // Zaman kuantumu (saniye cinsinden)
    private int quantum;
  
    public RoundRobin(List<Process> queue4) {
      this.queue4 = queue4;
      this.quantum = quantum;
    }
    
    // Görevlendiriciyi çalıştırır
    public void run() {
        for (Process process : queue4) {
            process.run();
        }
    }
}