import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Nested DS: HashMap<String, List<String>>
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // Sort the string to get the key
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // If key not present, initialize list
            map.computeIfAbsent(key, k -> new ArrayList<>());

            // Add the original string to the list
            map.get(key).add(s);
        }

        // Return all lists as result
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> result = groupAnagrams(strs);

        System.out.println("Grouped Anagrams:");
        for (List<String> group : result) {
            System.out.println(group);
        }
    }
}
