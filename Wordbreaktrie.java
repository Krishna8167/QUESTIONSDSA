import java.util.*;

class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private TrieNode root = new TrieNode();

    // Insert word into Trie
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        // build Trie
        for (String word : wordDict) {
            insert(word);
        }

        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, memo);
    }

    private List<String> dfs(String s, int start, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
            return res;
        }

        TrieNode node = root;
        for (int end = start; end < s.length(); end++) {
            int idx = s.charAt(end) - 'a';
            if (node.children[idx] == null) break;
            node = node.children[idx];

            if (node.isEnd) {
                List<String> sublist = dfs(s, end + 1, memo);
                for (String sub : sublist) {
                    res.add(s.substring(start, end + 1) + (sub.isEmpty() ? "" : " " + sub));
                }
            }
        }

        memo.put(start, res);
        return res;
    }

    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
        System.out.println(sol.wordBreak(s, wordDict));
        // Output: [cat sand dog, cats and dog]
    }
}
