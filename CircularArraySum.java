public class MaxCircularSubarray {
    public static int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int maxSum = nums[0], curMax = 0;
        int minSum = nums[0], curMin = 0;

        for (int n : nums) {
            curMax = Math.max(curMax + n, n);  // Kadane max
            maxSum = Math.max(maxSum, curMax);

            curMin = Math.min(curMin + n, n);  // Kadane min
            minSum = Math.min(minSum, curMin);

            total += n;
        }

        // If all numbers are negative, return maxSum directly
        if (maxSum < 0) return maxSum;

        // Otherwise, compare normal max vs circular max
        return Math.max(maxSum, total - minSum);
    }

    public static void main(String[] args) {
        int[] nums = {5, -3, 5};
        System.out.println("Max Circular Subarray Sum: " +
                           maxSubarraySumCircular(nums)); // Output: 10
    }
}
