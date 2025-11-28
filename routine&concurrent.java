import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyExample {

    // Routine 1: Simulates downloading a file
    public static void downloadTask(int id) {
        System.out.println("Downloading file " + id + " on: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000); // Simulate time-consuming work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished downloading file " + id);
    }

    // Routine 2: Simulates processing a file
    public static void processTask(int id) {
        System.out.println("Processing file " + id + " on: " + Thread.currentThread().getName());
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing file " + id);
    }

    public static void main(String[] args) {
        // Using a thread pool with 3 concurrent worker threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit multiple concurrent tasks
        for (int i = 1; i <= 5; i++) {
            int fileId = i;

            // Run both routines concurrently
            executor.submit(() -> downloadTask(fileId));
            executor.submit(() -> processTask(fileId));
        }

        executor.shutdown();
        System.out.println("All tasks submitted...");
    }
}
