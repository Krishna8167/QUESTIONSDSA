public class SubsetSumRecursion {

    // Recursive method to check if subset with target sum exists
    public static boolean isSubsetSum(int[] arr, int n, int target) {
        // Base cases
        if (target == 0) return true;
        if (n == 0) return false;

        if (arr[n - 1] > target) {
            // Skip element if it's greater than remaining sum
            return isSubsetSum(arr, n - 1, target);
        }

        // Include OR exclude the current element
        return isSubsetSum(arr, n - 1, target) || isSubsetSum(arr, n - 1, target - arr[n - 1]);
    }

    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int sum = 9;

        boolean exists = isSubsetSum(set, set.length, sum);
        System.out.println("Subset with sum " + sum + " exists? " + exists);
    }
}
