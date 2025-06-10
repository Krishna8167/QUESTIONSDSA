/**
 * FibonacciSeries.java
 * 
 * This program prints the Fibonacci series up to a specified number of terms
 * using a recursive method.
 * 
 * Author: [Your Name]
 * Date: [Today's Date]
 */

public class fibonacciseries {

    // Recursive method to calculate nth Fibonacci number
    public static int fibonacci(int n) {
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int terms = 10; // Change this to generate more terms
        System.out.println("Fibonacci Series up to " + terms + " terms:");

        for (int i = 0; i < terms; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}
