import java.util.*;

/**
 * @author Ritwik Banerjee
 * @author Daniel Garcia
 */
public class PriorityQueue<T> implements Heap<T> {

    private transient int           size;
    private transient ArrayList<T>  queue;
    private transient Comparator<T> comparator;

    public static <E> PriorityQueue<E> fromCollection(Collection<? extends E> c, Comparator<E> comparator) {
        PriorityQueue<E> heap = new PriorityQueue(comparator);
        ArrayList<E> temp = new ArrayList<E>(c);
        for (int i = 0; i < temp.size(); i++) {
            E o = temp.get(i);
            heap.insert(o);
        }
        return heap;
    }

    public PriorityQueue (Comparator<T> comparator) {
        size = 0;
        queue = new ArrayList<T>(0);
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    public int size() {
        return size;
    }

    public T findBest() {
        return queue.get(0);
    }

    public void insert (T t) {
        size++;
        if (size == 1) {
            queue.add(t);
        }
        else {
            int position = size - 1;
            queue.add(t);
            T parent = queue.get((position - 1)/2);
            while (position > 0 && comparator.compare(t, parent) > 0) {
                swap(t, parent);
                position = (position - 1)/2;
                parent = getParent(t);
            }
        }
    }

    public T deleteBest() {
        if (queue.isEmpty()) throw new NoSuchElementException();
        T temp = queue.get(0);
        T movedElement = queue.get(size - 1);
        queue.set(0, movedElement);
        queue.remove(size - 1);
        size--;
        T leftChild = null;
        T rightChild = null;
        try {
            leftChild = getLeftChild(movedElement);
        }
        catch (NullPointerException e) {
            return temp;
        }
        try {
            rightChild = getRightChild(movedElement);
        }
        catch (NullPointerException e) {
            if (comparator.compare(movedElement, leftChild) == -1) {
                swap(movedElement, leftChild);
            }
            return temp;
        }
        while (comparator.compare(movedElement, highestChild(leftChild, rightChild)) == -1) {
            int tempNum = queue.indexOf(highestChild(leftChild, rightChild));
            swap(movedElement, highestChild(leftChild, rightChild));
            movedElement = queue.get(tempNum);
            try {
                leftChild = getLeftChild(movedElement);
            }
            catch (NullPointerException e) {
                break;
            }
            try {
                rightChild = getRightChild(movedElement);
            }
            catch (NullPointerException e) {
                if (comparator.compare(movedElement, leftChild) == -1) {
                    swap(movedElement, leftChild);
                }
                break;
            }
        }
        return temp;
    }

    public void clear() {
        size = 0;
        queue = new ArrayList<T>(0);
    }

    @Override
    public int hashCode() {
        return queue.hashCode();
    }

    public void swap(T t1, T t2) {
        T temp = queue.get(queue.indexOf(t1));
        int tempNum = queue.indexOf(t2);
        queue.set(queue.indexOf(t1), queue.get(tempNum));
        queue.set(tempNum, temp);
    }

    public T getParent(T t) {
        return queue.get((queue.indexOf(t) - 1)/2);
    }

    public T getLeftChild(T t) {
        if (2 * (queue.indexOf(t)) + 1 >= size) throw new NullPointerException();
        return queue.get(2 * (queue.indexOf(t)) + 1);
    }

    public T getRightChild(T t) {
        if (2 * (queue.indexOf(t)) + 2 >= size) throw new NullPointerException();
        return queue.get(2 * (queue.indexOf(t)) + 2);
    }

    public T highestChild(T leftChild, T rightChild) {
        int value = comparator.compare(leftChild, rightChild);
        T finalChild;
        if (value == -1) finalChild = rightChild;
        else finalChild  = leftChild;
        return finalChild;
    }

    @Override
    public String toString() {
        String all = "";
        for (int i = 0; i < queue.size(); i++) {
            String temp = queue.get(i).toString();
            all += temp + "\n";
        }
        return all;
    }
}