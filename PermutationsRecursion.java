import java.util.*;

public class PermutationsRecursion {

    public static void permute(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);
                permute(nums, used, current, result);
                current.remove(current.size() - 1); // backtrack
                used[i] = false;
            }
        }
    }

    public static List<List<Integer>> getPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3};
        List<List<Integer>> perms = getPermutations(input);
        System.out.println("Permutations of " + Arrays.toString(input) + ":");
        for (List<Integer> perm : perms) {
            System.out.println(perm);
        }
    }
}
