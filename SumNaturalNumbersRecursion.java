public class SumNaturalNumbersRecursion {

    // Recursive method to calculate sum of first n natural numbers
    public static int sum(int n) {
        if (n == 0) return 0; // Base case
        return n + sum(n - 1); // Recursive step
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Sum of first " + n + " natural numbers is: " + sum(n));
    }
}
