import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesesRecursion {

    public static void generate(int open, int close, String current, List<String> result) {
        if (open == 0 && close == 0) {
            result.add(current);
            return;
        }

        // Add open parenthesis if available
        if (open > 0) {
            generate(open - 1, close, current + "(", result);
        }

        // Add close parenthesis if valid
        if (close > open) {
            generate(open, close - 1, current + ")", result);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(n, n, "", result);
        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        List<String> combinations = generateParenthesis(n);

        System.out.println("Valid parentheses combinations for n = " + n + ":");
        for (String s : combinations) {
            System.out.println(s);
        }
    }
}
