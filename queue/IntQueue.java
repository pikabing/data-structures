public class IntQueue {

    private int[] arr;
    private int head, tail, size;

    public IntQueue(int size) {
        head = tail = 0;
        this.size = size + 1;
        arr = new int[this.size];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        if(head > tail) return size - tail + head;
        return tail - head;
    }

    public int peek() {
        return arr[head];
    }

    public void enqueue(int value) {
        arr[tail] = value;
        if(++tail == size) tail =0;
        if(tail == head) throw new RuntimeException("Queue too small");
    }

    public int dequeue() {
        int a = arr[head];
        if(++head == size) head = 0;
        return a;
    }
    public static void main(String[] args) {
       
        IntQueue q = new IntQueue(5);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        System.out.println(q.size());
        System.out.println(q.dequeue()); // 1
        System.out.println(q.dequeue()); // 2
        System.out.println(q.dequeue()); // 3
        System.out.println(q.dequeue()); // 4
        System.out.println(q.size());

        System.out.println(q.isEmpty()); // false

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println(q.dequeue()); // 5
        System.out.println(q.dequeue()); // 1
        System.out.println(q.dequeue()); // 2
        System.out.println(q.dequeue()); // 3

        System.out.println(q.isEmpty()); // true

    }
}