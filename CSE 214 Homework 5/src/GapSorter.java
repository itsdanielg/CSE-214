import java.util.List;

/**
 * @author Daniel Garcia
 */
public class GapSorter<E extends Comparable<E>> implements Sorter<E>{

    public void sort(List<E> eList, Order order) {
        int gap = eList.size()/2;
        while (gap > 1) {
            for (int i = 0; i < eList.size(); i++) {
                if (gap <= i) {
                    E temp = eList.get(i);
                    E toCompare = eList.get(i - gap);
                    if (order.equals(Order.ASCENDING)) {
                        while (i >= gap && eList.get(i).compareTo(toCompare) < 0) {
                            eList.set(i, toCompare);
                            eList.set(i - gap, temp);
                        }
                    }
                    else {
                        while (i >= gap && eList.get(i).compareTo(toCompare) > 0) {
                            eList.set(i, toCompare);
                            eList.set(i - gap, temp);
                        }
                    }
                }
            }
            gap /= 2;
        }
        if (gap == 1) {
            InsertionSorter<E> finalSort = new InsertionSorter();
            finalSort.sort(eList, order);
        }
    }
}
