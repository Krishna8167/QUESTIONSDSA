import java.util.HashSet;
import java.util.Set;

public class WordBreakRecursion {

    // Recursive word break function
    public static boolean wordBreak(String s, Set<String> dict) {
        // Base case: string is empty, successful segmentation
        if (s.isEmpty()) return true;

        // Try every prefix
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            String suffix = s.substring(i);

            if (dict.contains(prefix) && wordBreak(suffix, dict)) {
                return true;
            }
        }

        return false; // No valid segmentation found
    }

    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("leet");
        dictionary.add("code");
        dictionary.add("love");
        dictionary.add("you");

        String input = "leetcode";
        boolean canSegment = wordBreak(input, dictionary);

        System.out.println("Can \"" + input + "\" be segmented? " + canSegment);
    }
}
