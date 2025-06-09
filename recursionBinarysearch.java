public class recursionBinarysearch {

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 10, 14, 18, 21};
        int target = 14;
        int index = BiSearch(target, 0, arr.length - 1, arr);
        System.out.println("Index of target: " + index);
    }

    static int BiSearch(int target, int s, int e, int[] arr) {
        if (s > e) {
            return -1;
        }

        int mid = s + (e - s) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (target < arr[mid]) {
            return BiSearch(target, s, mid - 1, arr);
        } else {
            return BiSearch(target, mid + 1, e, arr);
        }
    }
}
