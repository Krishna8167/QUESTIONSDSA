import java.util.*;

public class ExpressionAddOperators {

    public static void addOperators(String num, int target, int index, long prevOperand, long currentValue,
                                    String expression, List<String> result) {
        if (index == num.length()) {
            if (currentValue == target) {
                result.add(expression);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            // Avoid numbers with leading 0
            if (i != index && num.charAt(index) == '0') break;

            String currentStr = num.substring(index, i + 1);
            long currentNum = Long.parseLong(currentStr);

            if (index == 0) {
                // First number, no operator
                addOperators(num, target, i + 1, currentNum, currentNum, currentStr, result);
            } else {
                // +
                addOperators(num, target, i + 1, currentNum, currentValue + currentNum, expression + "+" + currentStr, result);

                // -
                addOperators(num, target, i + 1, -currentNum, currentValue - currentNum, expression + "-" + currentStr, result);

                // *
                addOperators(num, target, i + 1,
                        prevOperand * currentNum,
                        currentValue - prevOperand + (prevOperand * currentNum),
                        expression + "*" + currentStr, result);
            }
        }
    }

    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        addOperators(num, target, 0, 0, 0, "", result);
        return result;
    }

    public static void main(String[] args) {
        String num = "123";
        int target = 6;

        List<String> expressions = addOperators(num, target);
        System.out.println("Valid expressions for target " + target + ":");
        for (String expr : expressions) {
            System.out.println(expr);
        }
    }
}
