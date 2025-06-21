public class PowerRecursion {

    // Recursive method to calculate a^b
    public static int power(int base, int exponent) {
        if (exponent == 0) return 1; // Base case
        return base * power(base, exponent - 1); // Recursive step
    }

    public static void main(String[] args) {
        int a = 2, b = 5;
        System.out.println(a + " raised to the power " + b + " is: " + power(a, b));
    }
}
