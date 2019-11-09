import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

    private int nodeCount = 0;
    private Node root = null;

    private class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public boolean isEmpty() { return size() == 0;}

    private int size() {return nodeCount;}

    private Node add(Node node, T elem) {
        if(node == null) {node = new Node(null, null, elem);}
        else {
            int i = elem.compareTo(node.data);

            if(i < 0) {
                node.left = add(node.left, elem);
            } else {
                node.right = add(node.right, elem);
            }
        }

        return node;
    }

    public boolean add(T elem) {
        if(contains(elem)) return false;
        root = add(root, elem);
        nodeCount++;
        return true;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node node, T elem) {
        if(node == null) return false;
        
        int cmp = elem.compareTo(node.data);
        
        if(cmp < 0) return contains(node.left, elem);
        
        if(cmp > 0) return contains(node.right, elem);

        return true;
    }

    public boolean remove(T elem) {
        if(contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }

        return false;
    }

    private Node remove(Node node, T elem) {
        if(node == null) return null;
        int cmp = elem.compareTo(node.data);

        if(cmp > 0) {
            node.right = remove(node.right, elem);
        } else if(cmp < 0) {
            node.left = remove(node.left, elem);
        } else {
            if(node.left == null) {
                Node right = node.right;
                node.data = null;
                node = null;

                return right;
            } else if(node.right == null) {
                Node left = node.left;
                node.data = null;
                node = null;

                return left;
            } else {
                Node tmp = findMin(node.right);
                node.data = tmp.data;
                node.right = remove(node.right, tmp.data);
            }
        }

        System.out.println(node.data);
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public Node getRoot() {
        return root;
    }

    private Iterator<T> preOrderTraversal() {

        final int count = nodeCount;
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                Node node = stack.pop();
                if(node.left != null) stack.push(node.left);
                if(node.right != null) stack.push(node.right);

                return node.data;
            }
        };

    }

    private Iterator<T> inOrderTraversal() {
        final int count = nodeCount;
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {

            Node trav = root;

            @Override
            public boolean hasNext() {
                return root!=null && !stack.isEmpty();
            }

            @Override
            public T next() {
                while(trav != null || trav.left != null) {
                    stack.push(trav);
                    trav = trav.left;
                }

                Node node = stack.pop();

                while(node.right != null) {
                    stack.push(node.right);
                    node = node.right;
                }

                return node.data;
            }


        };
    }

    public Iterator<T> postOrderTraversal() {

        final int count = nodeCount;
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack1 = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            while(node != null) {
                stack1.push(node);
                if(node.left != null) stack1.push(node.left);
                if(node.right != null) stack1.push(node.right);
            }
        }

        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return !stack1.isEmpty() && root!=null;
            }

            @Override
            public T next() {
                return stack1.pop().data;
            }
        };
    }

    public Iterator<T> levelOrderTraversal() {

        final int count = nodeCount;
        final Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if(count != nodeCount) throw new ConcurrentModificationException();
                return root!=null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if(count != nodeCount) throw new ConcurrentModificationException();
                Node node = queue.poll();
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
                return node.data;
            }
        };

    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(11);
        b.add(5);
        b.add(1);
        b.add(3);
        b.add(12);
        b.add(13);
        b.add(20);
        Iterator<Integer> i = b.levelOrderTraversal();
        while(i.hasNext()) {
            System.out.println(i.next());
        }
    }
} 