import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {

        // Input array
        int[] numbers = {1, 2, 3, 2, 4, 1, 5, 2, 3, 1};

        // Creating HashMap to store element -> frequency
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        // Counting frequency
        for (int num : numbers) {
            // If key exists, increment value
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Displaying the HashMap
        System.out.println("Element Frequency:");
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
