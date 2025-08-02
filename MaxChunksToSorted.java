import java.util.*;

public class MaxChunksToSorted {

    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] leftMax = new int[n];
        int[] rightMin = new int[n + 1];

        // Build left max array
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }

        // Build right min array
        rightMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }

        // Count the chunks
        int chunks = 0;
        for (int i = 0; i < n; i++) {
            if (leftMax[i] <= rightMin[i + 1]) {
                chunks++;
            }
        }

        return chunks;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        System.out.println("Max chunks: " + maxChunksToSorted(arr));  // Output: 1

        int[] arr2 = {2, 1, 3, 4, 4};
        System.out.println("Max chunks: " + maxChunksToSorted(arr2)); // Output: 4
    }
}
