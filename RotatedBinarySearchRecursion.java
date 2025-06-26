public class RotatedBinarySearchRecursion {

    // Recursive method to search in a rotated sorted array
    public static int rotatedBinarySearch(int[] arr, int left, int right, int target) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) return mid;

        // Left half is sorted
        if (arr[left] <= arr[mid]) {
            if (target >= arr[left] && target < arr[mid]) {
                return rotatedBinarySearch(arr, left, mid - 1, target);
            } else {
                return rotatedBinarySearch(arr, mid + 1, right, target);
            }
        }

        // Right half is sorted
        if (target > arr[mid] && target <= arr[right]) {
            return rotatedBinarySearch(arr, mid + 1, right, target);
        } else {
            return rotatedBinarySearch(arr, left, mid - 1, target);
        }
    }

    public static void main(String[] args) {
        int[] arr = {13, 18, 25, 2, 8, 10};
        int target = 8;

        int index = rotatedBinarySearch(arr, 0, arr.length - 1, target);
        if (index != -1) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found.");
        }
    }
}
