import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {

        // Creating a HashMap
        HashMap<String, Integer> studentMarks = new HashMap<>();

        // Adding key-value pairs to the HashMap
        studentMarks.put("Amit", 85);
        studentMarks.put("Rahul", 92);
        studentMarks.put("Sneha", 78);
        studentMarks.put("Priya", 90);

        // Printing the entire HashMap
        System.out.println("Student Marks: " + studentMarks);

        // Accessing a value using key
        System.out.println("Marks of Rahul: " + studentMarks.get("Rahul"));

        // Checking if a key exists
        if (studentMarks.containsKey("Sneha")) {
            System.out.println("Sneha is present in the record.");
        }

        // Replace a value
        studentMarks.put("Amit", 88);
        System.out.println("Updated Marks of Amit: " + studentMarks.get("Amit"));

        // Removing a key-value pair
        studentMarks.remove("Priya");
        System.out.println("After removing Priya: " + studentMarks);

        // Iterating over HashMap using entrySet()
        System.out.println("\nIterating over the HashMap:");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // HashMap size
        System.out.println("\nTotal students in record: " + studentMarks.size());
    }
}
