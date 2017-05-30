import java.util.EmptyStackException;

/**
 * @author Daniel Garcia
 */
public class Stack<E> {

    private int size;
    private Node top;

    public Stack() {
        size = 0;
        top = null;
    }

    private static class Node<E> {
        private E data;
        private Node<E> prev;
        public Node(E data, Node<E> prev) {
            this.data = data;
            this.prev = prev;
        }
        public E getData() {
            return data;
        }
        public void setData(E data) {
            this.data = data;
        }
        public Node<E> getPrev() {
            return prev;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    public boolean isEmpty() {
        if (top == null) {
            return true;
        }
        else return false;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) top.getData();
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E tempData;
        tempData = (E) top.getData();
        top = top.getPrev();
        size--;
        return tempData;
    }

    public void push(E data) {
        Node<E> newNode = new Node<E>(data, null);
        newNode.setPrev(top);
        top = newNode;
        size++;
    }

    public int size() {
        return size;
    }

}
