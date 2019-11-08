import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private class Node<T> {
        private T data;
        private Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private int size;
    private Node<T> head = null, tail = null;

    public int size() {return size;}

    public void clear() {
        Node<T> trav = head;
        while(trav != null) {
            Node<T> next = trav.next;
            next.prev = next.next = null;
            next.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    public boolean isEmpty() { return size == 0;}

    public void add(T elem) {
        addLast(elem);
    }

    public void addLast(T elem) {
        if(isEmpty()) {
            head = tail = new Node<T> (elem, null, null);
        } else {
            tail.next = new Node<T> (elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void addFirst(T elem) {
        if(isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.prev = new Node<T>(elem,null, head);
            head = head.prev;
        }
    }

    public void addAt(int index, T elem) {
        if(index < 0) {
            throw new IllegalArgumentException("Index value can't be less than 0");
        }

        if(index == 0) {
            addFirst(elem);
            return;
        }

        if(index == size) {
            addLast(elem);
            return;
        }
        Node<T> temp = head;
        for(int i =0; i!=index; i++) {
            temp = temp.next;
        }
        Node<T> newNode = new Node<T>(elem,temp, temp.next);
        temp.next.prev = newNode;
        temp.next = newNode;

        size++;
        return;
    }

    public T peekFirst() {
        if(isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    public T peekLast() {
        if(isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    private T remove(Node<T> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;
        node.data = null;
        node = node.prev = node.next= null;

        size--;

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
          throw new IllegalArgumentException();
        }
    
        int i;
        Node<T> trav;
    
        if (index < size / 2) {
          for (i = 0, trav = head; i != index; i++) {
            trav = trav.next;
          }
        } else
          for (i = size - 1, trav = tail; i != index; i--) {
            trav = trav.prev;
          }
    
        return remove(trav);
      }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;
            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
               T data = trav.data;
               trav = trav.next;
               return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            Node<T> trav = head;
            while (trav != null) {
            sb.append(trav.data + ", ");
            trav = trav.next;
            }
            sb.append(" ]");
            return sb.toString();
        }

}