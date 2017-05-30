import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Daniel Garcia
 */
public class WordCounter {

    public static final String FILEPATH = "C:\\Users\\Daniel\\IdeaProjects\\CSE 214 Homework 6\\src\\sample.txt"; // absolute path of file

    public static void main(String[] args) {
        File name = new File(FILEPATH);
        Scanner file = null;
        try {
            file = new Scanner(name);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Integer> map = new LinkedHashMap();
        while (file.hasNext()) {
            String word = file.next().replaceAll("[^a-z^A-Z ]", "").toLowerCase();
            Integer frequency = map.get(word);
            if (frequency == null) frequency = 1;
            else frequency++;
            map.put(word, frequency);
        }

        map = frequencySort(map);

        for (String key : map.keySet()) {
            System.out.printf("%-15s%4s\n", key, map.get(key));
        }
    }

    public static class frequencyComparator implements Comparator<String> {
        Map map;
        public frequencyComparator(Map map) {
            this.map = map;
        }
        @Override
        public int compare(String str1, String str2) {
            int firstNum = (Integer) map.get(str1);
            int secondNum = (Integer) map.get(str2);
            Comparable firstValue = firstNum;
            Comparable secondValue = secondNum;
            if (secondValue.compareTo(firstValue) != 0) return secondValue.compareTo(firstValue);
            else return str1.compareTo(str2);
        }
    }

    public static Map frequencySort (Map map) {
        Map newMap = new TreeMap(new WordCounter.frequencyComparator(map));
        newMap.putAll(map);
        return newMap;
    }

}
