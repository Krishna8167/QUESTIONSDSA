// Author: Kris Shar
// Topic: Advanced DSA - Trie (Prefix Tree) with Auto-Complete
// Description: Implements word insertion and prefix-based auto-suggestions

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}

public class TrieAutoComplete {
    private final TrieNode root;

    public TrieAutoComplete() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    // Search if a word exists
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null)
                return false;
        }
        return node.isEndOfWord;
    }

    // Auto-complete suggestion function
    public List<String> autoComplete(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null)
                return Collections.emptyList();
        }
        List<String> results = new ArrayList<>();
        dfs(node, prefix, results);
        return results;
    }

    // Depth-first traversal to collect all words
    private void dfs(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) results.add(prefix);
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            dfs(entry.getValue(), prefix + entry.getKey(), results);
        }
    }

    // Driver code
    public static void main(String[] args) {
        TrieAutoComplete trie = new TrieAutoComplete();

        // Insert words
        trie.insert("code");
        trie.insert("coder");
        trie.insert("coding");
        trie.insert("cognitive");
        trie.insert("comet");
        trie.insert("company");
        trie.insert("come");

        // Check if a word exists
        System.out.println("Does 'coding' exist? " + trie.search("coding"));
        System.out.println("Does 'cool' exist? " + trie.search("cool"));

        // Auto-complete suggestions
        System.out.println("\nAuto-complete for prefix 'co': " + trie.autoComplete("co"));
        System.out.println("Auto-complete for prefix 'cod': " + trie.autoComplete("cod"));
        System.out.println("Auto-complete for prefix 'come': " + trie.autoComplete("come"));
    }
}
