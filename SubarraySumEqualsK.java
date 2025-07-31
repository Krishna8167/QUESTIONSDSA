import java.util.HashMap;

public class SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        int count = 0, prefixSum = 0;
        HashMap<Integer, Integer> prefixMap = new HashMap<>();

        // Initialize map with prefix sum = 0 seen once
        prefixMap.put(0, 1);

        for (int num : nums) {
            prefixSum += num;

            // Check if there is a prefix that if removed gives sum = k
            if (prefixMap.containsKey(prefixSum - k)) {
                count += prefixMap.get(prefixSum - k);
            }

            // Update map with current prefix sum
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -2, 1, 1, 1};
        int k = 3;
        System.out.println("Subarrays with sum " + k + ": " + subarraySum(nums, k));
    }
}
