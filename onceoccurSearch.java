class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            // Ensure mid is even (so pairs are aligned)
            if (mid % 2 == 1) {
                mid--;
            }

            // If pair is valid, single element must be after mid
            if (nums[mid] == nums[mid + 1]) {
                start = mid + 2;
            } else {
                end = mid;
            }
        }

        return nums[start];
    }

    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,1,2,3,3,4,4,8,8};
        System.out.println(sol.singleNonDuplicate(nums)); // Output: 2
    }
}
