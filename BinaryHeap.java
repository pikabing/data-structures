import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
    private int size = 0;
    private int capacity = 0;
    private List<T> heap = null;

    public BinaryHeap(int size) {
        heap = new ArrayList<>(size);
        capacity = size;
    }

    public BinaryHeap(T[] elems) {
        size = capacity = elems.length;
        heap = new ArrayList<>(size);
        for(int i = 0; i< size; i++) heap.add(elems[i]);
        for(int i = Math.max(0, size/2 - 1); i>=0; i--) sink(i);
    }

    public BinaryHeap(Collection<T> elems) {
        this(elems.size());
        for (T elem : elems) add(elem);
    }

    public boolean isEmpty() {return size == 0;}

    public void clear() {
        for(int i =0; i<size; i++) {
            heap.set(i, null);
        }
        size = 0;
    }

    public int size() {return size;}

    public T peek() {
        if(isEmpty()) throw new IllegalStateException();
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    public boolean contains(T elem) {
        for (int i = 0; i < size; i++) if (heap.get(i).equals(elem)) return true;
        return false;
    }

    public void add(T elem) {
        if(elem == null) throw new IllegalStateException();

        if(size < capacity) {
            heap.set(size, elem);
        } else {
            heap.add(elem);
            capacity++;
        }

        swim(size);
        size++;
    }

    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);
    
        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }

    private void swim(int k) {

        int parent = (k - 1) /2;
        while(k > 0 && less(k, parent)) {
            swap(k, parent);
            k = parent;
            parent = (k-1)/2;
        }

    }

    private void sink(int k) {

        while(true) {
            int left = k*2 +1;
            int right = k*2 + 2;
            int smallest = left;

            if(right < size && less(right, smallest)) smallest = right;

            if(left > size || !less(k, smallest)) break;

            swap(k, smallest);
            k = smallest;
        }
    }

    public boolean remove(T element) {
        if (element == null) return false;
        for (int i = 0; i < size; i++) {
          if (element.equals(heap.get(i))) {
            removeAt(i);
            return true;
          }
        }
        return false;
    }

    private T removeAt(int i) {
        if(isEmpty()) return null;

        size--;
        T rem_val = heap.get(i);
        swap(i, size);

        heap.set(size, null);

        if(i == size) return rem_val;

        T elem = heap.get(i);
        sink(i);
        if(heap.get(i).equals(elem)) swim(i);

        return rem_val;
    }

    public boolean isMinHeap(int k) {
        if(k >= size) return true;

        int left = k*2 + 1;
        int right = k*2 + 2;
        
        if (left < size && !less(k, left)) return false;
        if (right < size && !less(k, right)) return false;

        return isMinHeap(left) && isMinHeap(right);
    }

    @Override
    public String toString() {
      return heap.toString();
    }

}
