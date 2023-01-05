import java.util.*;

public class Queue {
  private Queue queue;

  public Queue() {
    queue = new LinkedList<>();
  }

  public void enqueue(Process process) {
    queue.add(process);
  }

  public Process dequeue() {
    return queue.poll();
  }

  public Process peek() {
    return queue.peek();
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public void iterateList() {
    // Bir Iterator objesi oluşturun
    Iterator<Object> iterator = list.iterator();

    // Elemanlar arasında dolaşın
    while (iterator.hasNext()) {
        Object element = iterator.next();
        // Elemanı işleyin (yazdırın, değiştirin, vb.)
        System.out.println(element);
    }
  }
}
