import java.util.*;

public class EvaluateBracket {
    public static String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Build HashMap
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean insideBracket = false;

        // Step 2: Parse string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                insideBracket = true;
                key.setLength(0); // reset key
            } else if (c == ')') {
                insideBracket = false;
                String value = map.getOrDefault(key.toString(), "?");
                result.append(value);
            } else {
                if (insideBracket) {
                    key.append(c);
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        List<List<String>> knowledge = new ArrayList<>();
        knowledge.add(Arrays.asList("name", "bob"));
        knowledge.add(Arrays.asList("age", "two"));

        String s = "(name)is(age)yearsold";
        System.out.println(evaluate(s, knowledge)); 
        // Output: "bobistwoyearsold"
    }
}
