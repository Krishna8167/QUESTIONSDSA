import java.util.*;

public class PhoneLetterCombinations {

    private static final Map<Character, String> phoneMap = Map.of(
        '2', "abc", '3', "def",
        '4', "ghi", '5', "jkl",
        '6', "mno", '7', "pqrs",
        '8', "tuv", '9', "wxyz"
    );

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        backtrack(0, new StringBuilder(), digits, result);
        return result;
    }

    private static void backtrack(int index, StringBuilder current, String digits, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = phoneMap.get(digits.charAt(index));
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(index + 1, current, digits, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        List<String> combinations = letterCombinations(digits);
        System.out.println("Letter Combinations:");
        System.out.println(combinations);
    }
}
