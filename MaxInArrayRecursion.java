public class MaxInArrayRecursion {

    // Recursive method to find the maximum element in the array
    public static int findMax(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index]; // Base case

        int maxInRest = findMax(arr, index + 1);
        return Math.max(arr[index], maxInRest);
    }

    public static void main(String[] args) {
        int[] arr = {4, 8, 1, 9, 2, 10, 3};
        int max = findMax(arr, 0);
        System.out.println("Maximum element is: " + max);
    }
}
