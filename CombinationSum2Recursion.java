import java.util.*;

public class CombinationSum2Recursion {

    public static void combinationSum2(int[] candidates, int target, int index, List<Integer> current,
                                       List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            // Skip duplicates
            if (i > index && candidates[i] == candidates[i - 1]) continue;

            // No point in continuing if number exceeds target
            if (candidates[i] > target) break;

            current.add(candidates[i]);
            combinationSum2(candidates, target - candidates[i], i + 1, current, result); // i + 1 to not reuse
            current.remove(current.size() - 1); // backtrack
        }
    }

    public static List<List<Integer>> getUniqueCombinations(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Important to handle duplicates
        combinationSum2(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> combinations = getUniqueCombinations(candidates, target);

        System.out.println("Unique combinations that sum to " + target + ":");
        for (List<Integer> combo : combinations) {
            System.out.println(combo);
        }
    }
}
