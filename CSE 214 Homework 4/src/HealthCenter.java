import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ritwik Banerjee
 * @author Daniel Garcia
 */
public class HealthCenter {

    static final Path TREATMENTS_CACHE = Paths.get("C:\\Users\\Daniel\\IdeaProjects\\CSE 214 Homework 4\\src\\treatments.csv");

    public static void main(String... args) throws IOException {
        List<String>    lines      = Files.readAllLines(TREATMENTS_CACHE, StandardCharsets.UTF_8);
        List<Treatment> treatments = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.trim().split(",");
            treatments.add(new Treatment(parts[0],
                    parts[1],
                    Double.parseDouble(parts[2]),
                    Double.parseDouble(parts[3])));
        }
        PriorityQueue<Treatment> pq1 = PriorityQueue.fromCollection(treatments,
                new Treatment.PriceBasedTreatmentComparator());
        System.out.println(pq1.toString());

        PriorityQueue<Treatment> pq2 = PriorityQueue.fromCollection(treatments,
                new Treatment.SuccessBasedTreatmentComparator());
        System.out.println(pq2.toString());
    }

}