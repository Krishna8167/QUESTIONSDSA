import java.util.Arrays;

public class RotateArrayWithSlice {
    public static int[] rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // handle k > n

        // "Slice" last k elements
        int[] lastPart = Arrays.copyOfRange(nums, n - k, n);

        // "Slice" first n-k elements
        int[] firstPart = Arrays.copyOfRange(nums, 0, n - k);

        // Concatenate slices
        int[] rotated = new int[n];
        System.arraycopy(lastPart, 0, rotated, 0, lastPart.length);
        System.arraycopy(firstPart, 0, rotated, lastPart.length, firstPart.length);

        return rotated;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        int[] rotated = rotate(nums, k);

        System.out.println("Original: " + Arrays.toString(nums));
        System.out.println("Rotated: " + Arrays.toString(rotated));
    }
}
