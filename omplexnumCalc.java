import java.util.*;

class Complex {
    int real, imag;

    Complex(int r, int i) {
        real = r;
        imag = i;
    }

    // Addition
    Complex add(Complex c) {
        return new Complex(real + c.real, imag + c.imag);
    }

    // Subtraction
    Complex subtract(Complex c) {
        return new Complex(real - c.real, imag - c.imag);
    }

    // Multiplication
    Complex multiply(Complex c) {
        int r = real * c.real - imag * c.imag;
        int i = real * c.imag + imag * c.real;
        return new Complex(r, i);
    }

    @Override
    public String toString() {
        return real + " + " + imag + "i";
    }
}

public class ComplexCalculator {
    public static Complex evaluateExpression(List<String> tokens) {
        Stack<Complex> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                Complex b = stack.pop();
                Complex a = stack.pop();
                stack.push(a.add(b));
            } else if (token.equals("-")) {
                Complex b = stack.pop();
                Complex a = stack.pop();
                stack.push(a.subtract(b));
            } else if (token.equals("*")) {
                Complex b = stack.pop();
                Complex a = stack.pop();
                stack.push(a.multiply(b));
            } else {
                // Parse complex number "a+bi"
                String[] parts = token.split("\\+|i");
                int real = Integer.parseInt(parts[0].trim());
                int imag = Integer.parseInt(parts[1].trim());
                stack.push(new Complex(real, imag));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        // Expression in Reverse Polish Notation: (3+2i) + (1+4i) * (2+3i)
        List<String> tokens = Arrays.asList("3+2i", "1+4i", "2+3i", "*", "+");

        Complex result = evaluateExpression(tokens);
        System.out.println("Result: " + result);
    }
}
