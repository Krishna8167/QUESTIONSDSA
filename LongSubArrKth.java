import java.util.*;

public class LongestSubarrayDivisibleByK {
    public static int longestSubarray(int[] nums, int k) {
        Map<Integer, Integer> modIndex = new HashMap<>();
        modIndex.put(0, -1); // base case: sum=0 before array starts
        
        int prefixSum = 0, maxLen = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int mod = prefixSum % k;
            
            // Handle negative mods
            if (mod < 0) mod += k;
            
            if (modIndex.containsKey(mod)) {
                maxLen = Math.max(maxLen, i - modIndex.get(mod));
            } else {
                modIndex.put(mod, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {2, -2, 2, -4};
        int k = 6;
        System.out.println("Longest Subarray Length: " + longestSubarray(nums, k)); 
        // Output: 4
    }
}
