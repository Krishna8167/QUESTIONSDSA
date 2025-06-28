public class CountOccurrencesRecursion {

    public static int findFirst(int[] arr, int low, int high, int target) {
        if (low > high) return -1;

        int mid = low + (high - low) / 2;

        if ((mid == 0 || arr[mid - 1] < target) && arr[mid] == target)
            return mid;
        else if (arr[mid] < target)
            return findFirst(arr, mid + 1, high, target);
        else
            return findFirst(arr, low, mid - 1, target);
    }

    public static int findLast(int[] arr, int low, int high, int target) {
        if (low > high) return -1;

        int mid = low + (high - low) / 2;

        if ((mid == arr.length - 1 || arr[mid + 1] > target) && arr[mid] == target)
            return mid;
        else if (arr[mid] > target)
            return findLast(arr, low, mid - 1, target);
        else
            return findLast(arr, mid + 1, high, target);
    }

    public static int countOccurrences(int[] arr, int target) {
        int first = findFirst(arr, 0, arr.length - 1, target);
        int last = findLast(arr, 0, arr.length - 1, target);

        if (first == -1 || last == -1) return 0;

        return last - first + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;

        int count = countOccurrences(arr, target);
        System.out.println("Number of occurrences of " + target + ": " + count);
    }
}
