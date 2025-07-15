import java.util.*;

public class SubsetsWithDuplicatesRecursion {

    public static void subsetsWithDup(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            // Skip duplicates at the same recursion level
            if (i > index && nums[i] == nums[i - 1]) continue;

            current.add(nums[i]);
            subsetsWithDup(nums, i + 1, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }

    public static List<List<Integer>> getUniqueSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort to handle duplicates
        subsetsWithDup(nums, 0, new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};

        List<List<Integer>> subsets = getUniqueSubsets(nums);
        System.out.println("Unique subsets:");
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }
}
