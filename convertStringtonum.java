class Solution {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        int result = 0;
        int sign = 1;

        // 1. Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') i++;

        // 2. Check for sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. Read digits only
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // 4. Check for overflow/underflow
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        // 5. Return result with sign
        return result * sign;
    }
}
