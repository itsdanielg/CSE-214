import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Garcia
 */
public class MergeSorter<E extends Comparable<E>> implements Sorter<E>{

    List<E> temp;

    public void sort(List<E> eList, Order order) {
        if (eList.size() < 2) return;
        List<E> left = new ArrayList(0);
        List<E> right = new ArrayList(0);
        for (int i = 0; i < eList.size(); i++) {
            E x = eList.get(i);
            if (i < eList.size()/2) left.add(x);
            else right.add(x);
        }
        sort(left, order);
        sort(right, order);
        temp = merge(left, right, order);
        for (int i = 0; i < eList.size(); i++) {
            eList.set(i, temp.get(i));
        }
    }

    protected List<E> merge(List<E> left, List<E> right, Order order) {
        List<E> result = new ArrayList(0);
        if (order.equals(Order.ASCENDING)) {
            while (!(left.isEmpty()) && !(right.isEmpty())) {
                if (left.get(0).compareTo(right.get(0)) <= 0) result.add(left.remove(0));
                else result.add(right.remove(0));
            }
            while (!(left.isEmpty())) result.add(left.remove(0));
            while (!(right.isEmpty())) result.add(right.remove(0));
        }
        else {
            while (!(left.isEmpty()) && !(right.isEmpty())) {
                if (left.get(0).compareTo(right.get(0)) >= 0) result.add(left.remove(0));
                else result.add(right.remove(0));
            }
            while (!(left.isEmpty())) result.add(left.remove(0));
            while (!(right.isEmpty())) result.add(right.remove(0));
        }
        return result;
    }
}
