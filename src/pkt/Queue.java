package pkt;

import java.util.*;

public class Queue {
    private Queue<Process> queue;

    public Kuyruk() {
        queue = new LinkedList<>();
    }

    public void enqueue(Process process) {
        queue.add(process);

    }

    public  Process dequeue() {
        return queue.poll(); //kaldırırsa true ya da false remove exception fırlatıyor
    }

    public Process peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

