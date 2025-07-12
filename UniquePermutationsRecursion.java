import java.util.*;

public class UniquePermutationsRecursion {

    public static void permuteUnique(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip used elements
            if (used[i]) continue;

            // Skip duplicates: only allow the first occurrence in the same recursion level
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            current.add(nums[i]);
            permuteUnique(nums, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public static List<List<Integer>> getUniquePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Important for duplicate handling
        permuteUnique(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        int[] input = {1, 1, 2};
        List<List<Integer>> perms = getUniquePermutations(input);
        System.out.println("Unique permutations of " + Arrays.toString(input) + ":");
        for (List<Integer> perm : perms) {
            System.out.println(perm);
        }
    }
}
