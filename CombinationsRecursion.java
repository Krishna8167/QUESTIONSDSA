import java.util.*;

public class CombinationsRecursion {

    public static void combine(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            combine(n, k, i + 1, current, result); // move to next
            current.remove(current.size() - 1);    // backtrack
        }
    }

    public static List<List<Integer>> getCombinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        int n = 4, k = 2;
        List<List<Integer>> combinations = getCombinations(n, k);

        System.out.println("Combinations of " + k + " out of " + n + ":");
        for (List<Integer> combo : combinations) {
            System.out.println(combo);
        }
    }
}
