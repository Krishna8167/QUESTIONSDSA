class Solution {
    // Main method to count occurrences
    public int countOccurrences(int[] arr, int target) {
        int first = firstOccurrence(arr, target);
        if (first == -1) return 0; // target not found
        int last = lastOccurrence(arr, target);
        return last - first + 1;
    }

    // Binary search for first occurrence
    private int firstOccurrence(int[] arr, int target) {
        int start = 0, end = arr.length - 1, result = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                result = mid;
                end = mid - 1; // keep searching left
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }

    // Binary search for last occurrence
    private int lastOccurrence(int[] arr, int target) {
        int start = 0, end = arr.length - 1, result = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                result = mid;
                start = mid + 1; // keep searching right
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,2,2,3,4,5,5,5,5,6,7};
        int target = 5;
        System.out.println("Occurrences of " + target + " = " + sol.countOccurrences(nums, target));
    }
}
