import java.util.List;

/**
 * @author Daniel Garcia
 */
public interface Sorter<E extends Comparable<E>> {
    void sort(List<E> eList, Order order);
}
