/**
 * @author Daniel Garcia
 */

import java.util.Iterator;
public class HotPotato<T> {

    CircularList<T> circle = new CircularList<T>();
    int m, n;

    public HotPotato(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public void add(T t) {
        if (n != circle.size()) {
            circle.add(t);
        }
        else {
            System.out.println("Maximum amount of players reached.");
        }
    }

    public void add(int index, T t) {
        if (n != circle.size()) {
            circle.add(index, t);
        }
        else {
            System.out.println("Maximum amount of players reached.");
        }
    }

    public void play() {
        if (circle.size() != n) {
            System.out.println("Maximum amount of players not reached. Please add more players.");
        }
        else {
            CircularList.CircularListIterator cursor = circle.new CircularListIterator();
            CircularList.CircularListIterator nextPlayer = circle.new CircularListIterator();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < m; j++) {
                    cursor.next();
                }
                for (int j = 0; j < m + 1; j++) {
                    nextPlayer.next();
                }
                String data = cursor.getCurrent().toString();
                circle.remove(cursor.getCurrent());
                cursor.setCurrent(nextPlayer.getCurrent());
                System.out.println("Eliminated person: " + data);
                System.out.println("Remaining circle: " + circle.toString());
                System.out.println("");
            }
            String winner = cursor.getCurrent().toString();
            System.out.println("Winner: " + winner);
        }

    }

}
