import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", s, 0, 0);
        return result;
    }

    private static void backtrack(List<String> result, String current, String s, int index, int parts) {
        if (parts == 4 && index == s.length()) {
            result.add(current.substring(0, current.length() - 1)); // remove trailing dot
            return;
        }
        if (parts > 4 || index >= s.length()) {
            return;
        }

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String segment = s.substring(index, index + len);
            if (isValid(segment)) {
                backtrack(result, current + segment + ".", s, index + len, parts + 1);
            }
        }
    }

    private static boolean isValid(String segment) {
        if (segment.length() > 1 && segment.startsWith("0")) return false;
        int val = Integer.parseInt(segment);
        return val >= 0 && val <= 255;
    }

    public static void main(String[] args) {
        String s = "25525511135";
        List<String> ips = restoreIpAddresses(s);
        System.out.println("Valid IPs:");
        for (String ip : ips) {
            System.out.println(ip);
        }
    }
}
