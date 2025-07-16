public class MinCutPalindromePartitionRecursion {

    public static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }

    public static int minCut(String s, int start) {
        if (start == s.length() || isPalindrome(s, start, s.length() - 1)) {
            return 0;
        }

        int minCuts = Integer.MAX_VALUE;

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                int cuts = 1 + minCut(s, end + 1); // 1 cut + recursion for the rest
                minCuts = Math.min(minCuts, cuts);
            }
        }

        return minCuts;
    }

    public static void main(String[] args) {
        String input = "aab";
        int result = minCut(input);
        System.out.println("Minimum cuts needed for palindrome partitioning of \"" + input + "\": " + result);
    }
}
