package pkt;

import java.util.*;

public class Queue {
    private LinkedList<Process> queue ;
    
    public Queue()
    {
    	queue = new LinkedList<>();
    }
    
    

    public void enqueue(Process process) {
        queue.add(process); //Kuyruga elaman ekleme
    }

    public  Process dequeue() {
        return queue.poll(); //Kuyruktan eleman silme
    }

    public Process peek() {
        return queue.peek(); //Kuyrugun ilk elemanini getirme
    }

    public boolean isEmpty() {
        return queue.isEmpty(); //Kuyruk bos mu degil mi?
    }
}

