public class MaxSumTwoNoOverlap {
    public static int maxSumTwoNoOverlap(int[] nums, int L, int M) {
        int n = nums.length;

        // Step 1: Compute prefix sums (to slice efficiently)
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 2: Initialize result
        int result = 0;

        // Step 3: Compute best L before M
        int maxL = 0;
        for (int i = L + M; i <= n; i++) {
            maxL = Math.max(maxL, prefix[i - M] - prefix[i - M - L]);
            result = Math.max(result, maxL + prefix[i] - prefix[i - M]);
        }

        // Step 4: Compute best M before L
        int maxM = 0;
        for (int i = L + M; i <= n; i++) {
            maxM = Math.max(maxM, prefix[i - L] - prefix[i - L - M]);
            result = Math.max(result, maxM + prefix[i] - prefix[i - L]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,6,5,2,2,5,1,9,4};
        int L = 1, M = 2;

        System.out.println("Max Sum of Non-Overlapping Subarrays: " +
                           maxSumTwoNoOverlap(nums, L, M));
    }
}
