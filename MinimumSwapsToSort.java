import java.util.*;

public class MinimumSwapsToSort {
    static int minSwaps(int[] nums) {
        int n = nums.length;
        int swaps = 0;

        // Pair each element with its index
        List<int[]> arrPos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrPos.add(new int[]{nums[i], i});
        }

        // Sort the array by value
        arrPos.sort(Comparator.comparingInt(a -> a[0]));

        // Track visited elements
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            // Already in correct place or already visited
            if (visited[i] || arrPos.get(i)[1] == i)
                continue;

            // Compute size of cycle
            int cycleSize = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = arrPos.get(j)[1];
                cycleSize++;
            }

            if (cycleSize > 0)
                swaps += (cycleSize - 1);
        }

        return swaps;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        System.out.println("Minimum swaps to sort: " + minSwaps(arr));  // Output: 2

        int[] arr2 = {1, 5, 4, 3, 2};
        System.out.println("Minimum swaps to sort: " + minSwaps(arr2));  // Output: 2
    }
}
