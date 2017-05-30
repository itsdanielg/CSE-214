import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Comparator;

/**
 * @author Daniel Garcia
 */
public class FileToMapLoader {

    public static final String FILEPATH = "C:\\Users\\Daniel\\IdeaProjects\\CSE 214 Homework 6\\src\\sample.csv"; // absolute path of file

    public enum Order {
        NAME("name"), PHONE("phone"), ORIGINAL("original");

        private final String order;

        Order(String order) {
            this.order = order;
        }

        public String toString() {
            return order;
        }
    }

    public static void main(String[] args) {
        Map<String, String> map;
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an ordering method (name, phone, or original): ");
        String order = input.next().toLowerCase();
        while (!(order.equals("name")) && !(order.equals("phone")) && !(order.equals("original"))) {
            System.out.print("Please enter an ordering method (name, phone, or original): ");
            order = input.next().toLowerCase();
        }

        if (order.equals(Order.ORIGINAL.toString())) map = new LinkedHashMap();
        else map = new TreeMap();

        File name = new File(FILEPATH);
        Scanner file = null;
        try {
            file = new Scanner(name);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] values  = line.split(", ");
            map.put(values[0], values[1]);
        }

        if (order.equals(Order.PHONE.toString())) map = phoneSort(map);

        for (String key : map.keySet()) {
            System.out.printf("%-20s%14s\n", key, map.get(key));
        }
    }

    public static class phoneComparator implements Comparator<String>{
        Map map;
        public phoneComparator(Map map) {
            this.map = map;
        }
        @Override
        public int compare(String str1, String str2) {
            String firstString = (String) map.get(str1);
            String secondString = (String) map.get(str2);
            Comparable firstValue = firstString;
            Comparable secondValue = secondString;
            return firstValue.compareTo(secondValue);
        }
    }

    public static Map phoneSort (Map map) {
        Map newMap = new TreeMap(new phoneComparator(map));
        newMap.putAll(map);
        return newMap;
    }

}
