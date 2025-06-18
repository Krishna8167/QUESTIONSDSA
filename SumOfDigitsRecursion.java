public class SumOfDigitsRecursion {

    // Recursive method to calculate sum of digits
    public static int sumOfDigits(int n) {
        if (n == 0) return 0;  // Base case
        return (n % 10) + sumOfDigits(n / 10);  // Recursive step
    }

    public static void main(String[] args) {
        int number = 12345;
        System.out.println("Sum of digits of " + number + " is: " + sumOfDigits(number));
    }
}
