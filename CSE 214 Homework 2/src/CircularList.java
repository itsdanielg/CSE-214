/**
 * @author Daniel Garcia
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularList<T> extends MyLinkedList<T> {

    public CircularList() {
        clear();
    }

    private int size = 0;

    @Override
    public void clear() {
        head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
    @Override
    public boolean add(T t) {
        add(this.size, t);
        return true;
    }

    @Override
    public void add(int index, T t) {
        if (index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (size() == 0) {
            head = new Node<T>(t, this.head, this.head);
        }
        else{
            Node<T> newNode = new Node<T>(t, null, null);
            CircularListIterator backCursor = new CircularListIterator();
            if (index == 0) {
                for (int i = 0; i < size() - 1; i++) {
                    backCursor.next();
                }
                newNode.next = head;
                newNode.prev = backCursor.current;
                head.prev = newNode;
                backCursor.current.next = newNode;
                head = newNode;
            }
            else {
                for (int i = 0; i < index - 1; i++) {
                    backCursor.next();
                }
                if (index == size()) {
                    newNode.next = head;
                    newNode.prev = backCursor.current;
                    head.prev = newNode;
                    backCursor.current.next = newNode;
                }
                else {
                    CircularListIterator frontCursor = new CircularListIterator();
                    for (int i = 0; i < index; i++) {
                        frontCursor.next();
                    }
                    newNode.next = backCursor.current.next;
                    newNode.prev = frontCursor.current.prev;
                    frontCursor.current.prev = newNode;
                    backCursor.current.next = newNode;
                }
            }
        }
        this.size++;
    }

    @Override
    public T get(int index) {
        return this.getNode(index).data;
    }

    @Override
    public T set(int index, T t) {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        if (index >= size()) {
            index %= size();
        }
        CircularListIterator cursor = new CircularListIterator();
        for (int i = 0; i < index; i++) {
            cursor.next();
        }
        T returnElement = cursor.current.data;
        cursor.current.data = t;
        return returnElement;
    }

    @Override
    public T remove(int index) {
        return remove(this.getNode(index));
    }

    @Override
    public T remove(Node<T> node) {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T returnElement =  node.data;
        if (node == head) {
            if (size == 1) {
                head = null;
            }
            else {
                CircularListIterator cursor = new CircularListIterator();
                for (int i = 0; i < size() - 1; i++) {
                    cursor.next();
                }
                head = head.next;
                head.prev = cursor.current;
                cursor.current.next = head;
            }
        }
        else if (node == this.getNode(size - 1)) {
            node.prev.next = head;
            head.prev = node.prev;
            node = null;
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node = null;
        }
        this.size--;
        return returnElement;
    }

    @Override
    public boolean remove(T t) {
        if (size() == 0) {
            return false;
        }
        CircularListIterator cursor = new CircularListIterator();
        if (head.data == t) {
            if (size() == 1) {
                head = null;
            }
            else {
                for (int i = 0; i < size() - 1; i++) {
                    cursor.next();
                }
                head = head.next;
                head.prev = cursor.current;
                cursor.current.next = head;
            }
            this.size--;
            return true;
        }
        else {
            for (int i = 0; i < size() - 1; i++) {
                cursor.next();
                if (cursor.current.data == t) {
                    if (i == size() - 2) {
                        cursor.current.prev.next = head;
                        head.prev = cursor.current.prev;
                        cursor.current = null;
                    }
                    else {
                        cursor.current.prev.next = cursor.current.next;
                        cursor.current.next.prev = cursor.current.prev;
                        cursor.current = null;
                    }
                    this.size--;
                    return true;
                }
            }
        }
        return false;
    }

    private Node<T> getNode(int index) {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        if (index >= size()) {
            index %= size();
        }
        CircularListIterator cursor = new CircularListIterator();
        for (int i = 0; i < index; i++) {
            cursor.next();
        }
        return cursor.current;
    }

    @Override
    public String toString() {
        CircularListIterator cursor = new CircularListIterator();
        String links = new String("");
        if (size() != 0) {
            for (int i = 0; i < size(); i++) {
                links += cursor.next() + " <==> ";
            }
        }
        return links;
    }

    public class CircularListIterator implements Iterator<T> {

        public Node<T> getCurrent() {
            return current;
        }

        public void setCurrent(Node<T> current) {
            this.current = current;
        }

        private Node<T> current      = head;
        private boolean removable    = false;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            T nextElement = current.data;
            current = current.next;
            removable = true;
            return nextElement;
        }

        /**
         * Removes from the underlying collection the last element returned by this iterator. This
         * method can be called only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the iteration is in
         * progress in any way other than by calling this method.
         *
         * @throws IllegalStateException if the {@code next} method has not yet been called, or the
         *                               {@code remove} method has already been called after the
         *                               last call to the {@code next} method
         */
        @Override
        public void remove() {
            if (!removable)
                throw new IllegalStateException();
            CircularList.this.remove(current.prev);
            removable = false;
        }
    }

}
