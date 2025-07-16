import java.util.*;

public class RestoreIPAddressesRecursion {

    public static void restore(String s, int index, int dots, String current, List<String> result) {
        if (dots == 4 && index == s.length()) {
            result.add(current.substring(0, current.length() - 1)); // remove last dot
            return;
        }

        if (dots > 4 || index >= s.length()) return;

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String segment = s.substring(index, index + len);
            
            // Check if segment is valid
            if ((segment.startsWith("0") && segment.length() > 1) || Integer.parseInt(segment) > 255)
                continue;

            restore(s, index + len, dots + 1, current + segment + ".", result);
        }
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() > 12 || s.length() < 4) return result;
        restore(s, 0, 0, "", result);
        return result;
    }

    public static void main(String[] args) {
        String input = "25525511135";
        List<String> ipAddresses = restoreIpAddresses(input);

        System.out.println("Possible valid IP addresses from \"" + input + "\":");
        for (String ip : ipAddresses) {
            System.out.println(ip);
        }
    }
}
