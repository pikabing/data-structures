import java.util.ArrayDeque;
import java.util.EmptyStackException;

public class IntStack {

    private int[] arr;
    private int size = 0;

    public IntStack(int size) {
        arr = new int[size];
    }

    public boolean isEmpty() {return size == 0;}

    public int size() {return size;}

    public int peek() {
        if(isEmpty()) throw new EmptyStackException();
        return arr[size - 1];
    }

    public void push(int e) {
        arr[size++] = e;
    }

    public int pop() {
        if(isEmpty()) throw new EmptyStackException();
        return arr[--size];
    }

    private static void benchMarkTest() {

        int n = 10000000;
        IntStack intStack = new IntStack(n);
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) intStack.push(i);
        for (int i = 0; i < n; i++) intStack.pop();
        long end = System.nanoTime();
        System.out.println("IntStack Time: " + (end - start) / 1e9);
    
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();       
        start = System.nanoTime();
        for (int i = 0; i < n; i++) arrayDeque.push(i);
        for (int i = 0; i < n; i++) arrayDeque.pop();
        end = System.nanoTime();
        System.out.println("ArrayDeque Time: " + (end - start) / 1e9);
      }

    public static void main(String[] args) {
        benchMarkTest();
    }
}