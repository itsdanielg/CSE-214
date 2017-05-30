import java.util.List;

/**
 * @author Daniel Garcia
 */
public class InsertionSorter<E extends Comparable<E>> implements Sorter<E> {

    public void sort(List<E> eList, Order order) {
        if (eList.size() < 2) return;
        int boundary = 1;
        while (boundary < eList.size()) {
            insert(eList, boundary, order);
            boundary++;
        }
    }

    protected void insert(List<E> eList, int boundary, Order order) {
        E toBeInserted = eList.remove(boundary);
        int elementCompared = boundary - 1;
        if (order.equals(Order.ASCENDING)) {
            while (toBeInserted.compareTo(eList.get(elementCompared)) < 0) {
                elementCompared--;
                if (elementCompared == -1) break;
            }
            eList.add(elementCompared + 1, toBeInserted);
        }
        else {
            while (toBeInserted.compareTo(eList.get(elementCompared)) > 0) {
                elementCompared--;
                if (elementCompared == -1) break;
            }
            eList.add(elementCompared + 1, toBeInserted);
        }
    }
}
