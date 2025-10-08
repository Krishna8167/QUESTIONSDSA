import java.util.HashMap;

class TrieNode {
    // Each node has a map for child bits (0 and 1)
    HashMap<Integer, TrieNode> children = new HashMap<>();
}

public class MaxXORUsingTrie {
    TrieNode root = new TrieNode();

    // Insert a number into the Trie
    public void insert(int num) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {  // 32-bit integer
            int bit = (num >> i) & 1;
            node.children.putIfAbsent(bit, new TrieNode());
            node = node.children.get(bit);
        }
    }

    // Find max XOR for a given number
    public int findMaxXOR(int num) {
        TrieNode node = root;
        int maxXor = 0;

        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int toggledBit = 1 - bit; // Opposite bit (maximize XOR)

            if (node.children.containsKey(toggledBit)) {
                maxXor |= (1 << i); // Add this bit to XOR result
                node = node.children.get(toggledBit);
            } else {
                node = node.children.get(bit);
            }
        }
        return maxXor;
    }

    public int findMaximumXOR(int[] nums) {
        // Step 1: Insert all numbers into Trie
        for (int num : nums) {
            insert(num);
        }

        // Step 2: Find max XOR for each number
        int maxResult = 0;
        for (int num : nums) {
            maxResult = Math.max(maxResult, findMaxXOR(num));
        }

        return maxResult;
    }

    // Main method
    public static void main(String[] args) {
        MaxXORUsingTrie solution = new MaxXORUsingTrie();
        int[] nums = {3, 10, 5, 25, 2, 8};

        int result = solution.findMaximumXOR(nums);
        System.out.println("Maximum XOR is: " + result);
    }
}
