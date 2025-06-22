public class StringPermutationsRecursion {

    // Recursive method to generate all permutations
    public static void permute(String str, String result) {
        if (str.isEmpty()) {
            System.out.println(result);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            String ch = str.charAt(i) + "";
            String left = str.substring(0, i);
            String right = str.substring(i + 1);
            permute(left + right, result + ch);
        }
    }

    public static void main(String[] args) {
        String input = "abc";
        System.out.println("Permutations of " + input + ":");
        permute(input, "");
    }
}
