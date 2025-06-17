public class ReverseStringRecursion {

    // Recursive method to reverse a string
    public static String reverse(String str) {
        if (str.isEmpty()) return str;  // Base case
        return reverse(str.substring(1)) + str.charAt(0);  // Recursive step
    }

    public static void main(String[] args) {
        String input = "hello";
        String reversed = reverse(input);
        System.out.println("Reversed string: " + reversed);
    }
}
