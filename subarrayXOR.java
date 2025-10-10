import java.util.HashMap;

public class SubarrayXOR {
    public static int countSubarraysXOR(int[] arr, int K) {
        int count = 0;
        int prefixXOR = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // base case

        for (int num : arr) {
            prefixXOR ^= num; // update prefix XOR

            // Check if (prefixXOR ^ K) has occurred before
            int requiredXOR = prefixXOR ^ K;
            count += map.getOrDefault(requiredXOR, 0);

            // Update map with current prefix XOR
            map.put(prefixXOR, map.getOrDefault(prefixXOR, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 6, 4};
        int K = 6;
        System.out.println("Count of subarrays with XOR = " + K + " : " + countSubarraysXOR(arr, K));
    }
}
