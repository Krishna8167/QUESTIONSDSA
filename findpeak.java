class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            // If mid is greater than next, peak is on left side (including mid)
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } 
            // Else, peak is on right side
            else {
                start = mid + 1;
            }
        }

        // start and end converge to the peak
        return start;
    }

    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1,2,3,1};
        int[] nums2 = {1,2,1,3,5,6,4};
        System.out.println(sol.findPeakElement(nums1)); // Output: 2
        System.out.println(sol.findPeakElement(nums2)); // Output: 1 or 5
    }
}
