import java.util.ArrayList;
import java.util.List;

public class AddOperators {

    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", num, target, 0, 0, 0);
        return result;
    }

    private static void backtrack(List<String> result, String expr, String num, int target, int index, long eval, long lastOperand) {
        if (index == num.length()) {
            if (eval == target) {
                result.add(expr);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') break; // Skip leading zeros
            String currentStr = num.substring(index, i + 1);
            long currentNum = Long.parseLong(currentStr);

            if (index == 0) {
                // First number, no operator
                backtrack(result, currentStr, num, target, i + 1, currentNum, currentNum);
            } else {
                backtrack(result, expr + "+" + currentStr, num, target, i + 1, eval + currentNum, currentNum);
                backtrack(result, expr + "-" + currentStr, num, target, i + 1, eval - currentNum, -currentNum);
                backtrack(result, expr + "*" + currentStr, num, target, i + 1,
                          eval - lastOperand + lastOperand * currentNum, lastOperand * currentNum);
            }
        }
    }

    public static void main(String[] args) {
        String digits = "123";
        int target = 6;
        List<String> expressions = addOperators(digits, target);
        System.out.println("Expressions that evaluate to " + target + ":");
        for (String exp : expressions) {
            System.out.println(exp);
        }
    }
}
