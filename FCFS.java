import java.io.*;
import java.util.*;

public class FCFS {

    private List<Process> processes;
  
    public FCFS(List<Process> processes)
    {
      this.processes = processes;
    }
  
    public void schedule()
    {
      Collections.sort(processes, (p1, p2) -> p1.arrivalTime - p2.arrivalTime);
  
      int currentTime = 0;
  
      for (Process p : processes) {
        if (currentTime < p.arrivalTime) 
        {
          currentTime = p.arrivalTime;
        }
        currentTime += p.duration;
        
        System.out.println("Process " + p.id + ": start at " + (currentTime - p.duration) + ", end at " + currentTime);
      }
    }
  }