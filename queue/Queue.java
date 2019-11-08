import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    public Queue() {}

    public Queue(T element) {
        offer(element);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T peek() {
        if(isEmpty()) throw new RuntimeException("Empty Queue");
        return list.peekFirst();
    }

    public T poll() {
        if(isEmpty()) throw new RuntimeException("Empty Queue");
        return list.removeFirst();
    }

    public void offer(T elem) {
        list.addLast(elem);
    }
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

}