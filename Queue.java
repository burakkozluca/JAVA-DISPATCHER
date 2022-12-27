import java.util.Queue;
import java.util.LinkedList;

public class Queue {
  private Queue<Process> queue;

  public ProcessQueue() {
    queue = new LinkedList<>();
  }

  public void enqueue(Process process) {
    queue.add(process);
  }

  public Process dequeue() {
    return queue.remove();
  }

  public Process peek() {
    return queue.peek();
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }
}
