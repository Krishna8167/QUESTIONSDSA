import java.util.*;

public class DecodeWays {

    public static int numDecodings(String s) {
        Map<Integer, Integer> memo = new HashMap<>();
        return decodeHelper(s, 0, memo);
    }

    private static int decodeHelper(String s, int index, Map<Integer, Integer> memo) {
        if (index == s.length()) return 1;
        if (s.charAt(index) == '0') return 0;
        if (memo.containsKey(index)) return memo.get(index);

        // Single digit decoding
        int count = decodeHelper(s, index + 1, memo);

        // Two digit decoding (if within 10-26)
        if (index + 1 < s.length()) {
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if (twoDigit >= 10 && twoDigit <= 26) {
                count += decodeHelper(s, index + 2, memo);
            }
        }

        memo.put(index, count);
        return count;
    }

    public static void main(String[] args) {
        String s = "226";
        System.out.println("Number of ways to decode \"" + s + "\": " + numDecodings(s));
    }
}
