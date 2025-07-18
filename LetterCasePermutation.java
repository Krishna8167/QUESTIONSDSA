import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {

    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }

    private static void backtrack(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        if (Character.isLetter(chars[index])) {
            // try lowercase
            chars[index] = Character.toLowerCase(chars[index]);
            backtrack(chars, index + 1, result);

            // try uppercase
            chars[index] = Character.toUpperCase(chars[index]);
            backtrack(chars, index + 1, result);
        } else {
            backtrack(chars, index + 1, result); // digit - just continue
        }
    }

    public static void main(String[] args) {
        String input = "a1b2";
        List<String> permutations = letterCasePermutation(input);
        System.out.println("All letter case permutations of \"" + input + "\":");
        for (String str : permutations) {
            System.out.println(str);
        }
    }
}
