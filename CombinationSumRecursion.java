import java.util.ArrayList;
import java.util.List;

public class CombinationSumRecursion {

    public static void combinationSum(int[] candidates, int target, int index, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current)); // Found valid combination
            return;
        }

        if (index == candidates.length || target < 0) return;

        // Include current candidate
        current.add(candidates[index]);
        combinationSum(candidates, target - candidates[index], index, current, result);
        current.remove(current.size() - 1); // Backtrack

        // Exclude current candidate and move to next
        combinationSum(candidates, target, index + 1, current, result);
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = new ArrayList<>();
        combinationSum(candidates, target, 0, new ArrayList<>(), result);

        System.out.println("Combinations summing to " + target + ":");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }
}
