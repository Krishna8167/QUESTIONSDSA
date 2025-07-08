public class RegexMatchingRecursion {

    public static boolean isMatch(String s, String p, int i, int j) {
        // Base case: pattern exhausted
        if (j == p.length()) return i == s.length();

        // Check if first characters match
        boolean currentMatch = (i < s.length()) && 
                               (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // Check for '*'
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // 2 cases:
            // 1. Don't use the '*': move pattern by 2
            // 2. Use the '*': move input if current characters match
            return isMatch(s, p, i, j + 2) ||
                   (currentMatch && isMatch(s, p, i + 1, j));
        }

        // Regular character or '.'
        return currentMatch && isMatch(s, p, i + 1, j + 1);
    }

    public static boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";

        boolean result = isMatch(s, p);
        System.out.println("Does \"" + s + "\" match regex \"" + p + "\"? " + result);
    }
}
