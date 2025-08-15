import java.util.Stack;

public class DecodeString {
    public static String decode(String s) {
        Stack<Integer> counts = new Stack<>();
        Stack<String> resultStack = new Stack<>();
        String result = "";
        int index = 0;

        while (index < s.length()) {
            char ch = s.charAt(index);

            if (Character.isDigit(ch)) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = count * 10 + (s.charAt(index) - '0');
                    index++;
                }
                counts.push(count);
            } 
            else if (ch == '[') {
                resultStack.push(result);
                result = "";
                index++;
            } 
            else if (ch == ']') {
                StringBuilder temp = new StringBuilder(resultStack.pop());
                int repeatTimes = counts.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(result);
                }
                result = temp.toString();
                index++;
            } 
            else {
                result += ch;
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String input = "3[a2[c]]";
        System.out.println("Decoded string: " + decode(input));
    }
}
