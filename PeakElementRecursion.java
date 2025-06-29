public class PeakElementRecursion {

    // Recursive function to find the peak in a unimodal array
    public static int findPeak(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;

        // Check if mid is the peak
        if ((mid == 0 || arr[mid - 1] <= arr[mid]) &&
            (mid == arr.length - 1 || arr[mid + 1] <= arr[mid])) {
            return mid;
        }

        // If left neighbor is greater, recurse left
        if (mid > 0 && arr[mid - 1] > arr[mid]) {
            return findPeak(arr, low, mid - 1);
        }

        // Otherwise, recurse right
        return findPeak(arr, mid + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 7, 8, 12, 9, 5, 2};
        int peakIndex = findPeak(arr, 0, arr.length - 1);

        System.out.println("Peak element is: " + arr[peakIndex] + " at index: " + peakIndex);
    }
}
