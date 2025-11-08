import java.util.*;

class ExpressionParser {

    // Method to define operator precedence
    private static int precedence(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    // Convert infix expression to postfix
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek()))
                    result.append(stack.pop());
                stack.push(ch);
            }
        }

        while (!stack.isEmpty())
            result.append(stack.pop());

        return result.toString();
    }

    // Evaluate postfix expression
    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.push(ch - '0');
            } else {
                int val2 = stack.pop();
                int val1 = stack.pop();
                switch (ch) {
                    case '+' -> stack.push(val1 + val2);
                    case '-' -> stack.push(val1 - val2);
                    case '*' -> stack.push(val1 * val2);
                    case '/' -> stack.push(val1 / val2);
                    case '^' -> stack.push((int) Math.pow(val1, val2));
                }
            }
        }

        return stack.pop();
    }
}

public class InfixToPostfixDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter infix expression (single-digit numbers only):");
        String infix = sc.nextLine();

        String postfix = ExpressionParser.infixToPostfix(infix);
        System.out.println("📘 Postfix Expression: " + postfix);

        int result = ExpressionParser.evaluatePostfix(postfix);
        System.out.println("✅ Evaluated Result: " + result);

        sc.close();
    }
}
