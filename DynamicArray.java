import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

    private T[] array;
    private int len = 0;
    private int capacity = 0;

    public DynamicArray() {this(16);}

    public DynamicArray(int capacity) {
        if(capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() { return len;}

    public boolean isEmpty() { return size() == 0;}

    public T get(int index) { return array[index];}

    public void set(int index, T element) {array[index] = element;}

    public void clear() {
        for(int i = 0; i< len; i++)
            array[i] = null;
        len = 0;
    }

    public void add(T element) {

        if(len+1 > capacity) {
            if(capacity == 0) capacity = 1;
            else capacity *= 2;
            T[] new_array = (T[]) new Object[capacity];
            for(int i =0; i< len; i++) new_array[i] = array[i];
            array = new_array;
        }

        array[len++] = element;
    }

    public T removeAt(int index) {

        if(index >= len || index < 0) throw new ArrayIndexOutOfBoundsException();
        T data = array[index];
        T[] new_arr = (T[]) new Object[len - 1];
        for(int i=0, j=0; i< len; i++, j++) {
            if(i == index) j--;
            else new_arr[j] = array[i];
        }
        array = new_arr;
        capacity = --len;
        return data;
    }

    public boolean remove(T element) {
        int index = indexOf(element);
        if(index == -1) return false;
        removeAt(index);
        return true;
    }

    public int indexOf(T element) {
        for(int i=0; i< len; i++) {
            if(element == null) {
                if(array[i] == null)
                    return i;
            } else {
                if(element.equals(array[i])){
                    return i;
                } 
            }  
        }
        return -1;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return array[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            } 
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
        StringBuilder sb = new StringBuilder(len).append("[");
        for (int i = 0; i < len - 1; i++) sb.append(array[i] + ", ");
        return sb.append(array[len - 1] + "]").toString();
        }
    }

    public static void main(String[] args) {
        
        DynamicArray<String> d = new DynamicArray<>(10);
        d.add("a");
        System.out.println(d.size());
    }
}