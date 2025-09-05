import java.util.*;

public class AnagramCheck {
    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        Map<Character, Integer> freq = new HashMap<>();

        // Count frequency of characters in s1
        for (char c : s1.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Reduce frequency using characters in s2
        for (char c : s2.toCharArray()) {
            if (!freq.containsKey(c)) return false;
            freq.put(c, freq.get(c) - 1);

            if (freq.get(c) == 0) {
                freq.remove(c);
            }
        }

        return freq.isEmpty();
    }

    public static void main(String[] args) {
        String s1 = "listen";
        String s2 = "silent";
        String s3 = "stone";

        System.out.println(s1 + " & " + s2 + " → " + areAnagrams(s1, s2)); // true
        System.out.println(s1 + " & " + s3 + " → " + areAnagrams(s1, s3)); // false
    }
}
