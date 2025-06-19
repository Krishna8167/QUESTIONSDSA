public class PalindromeRecursion {

    // Recursive method to check for palindrome
    public static boolean isPalindrome(String s, int start, int end) {
        if (start >= end) return true; // Base case
        if (s.charAt(start) != s.charAt(end)) return false;
        return isPalindrome(s, start + 1, end - 1); // Recursive step
    }

    public static void main(String[] args) {
        String str = "madam";
        boolean result = isPalindrome(str, 0, str.length() - 1);
        System.out.println(str + " is a palindrome? " + result);
    }
}
