import java.util.*;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;
}

public class AutocompleteTrie {
    private TrieNode root;

    public AutocompleteTrie() {
        root = new TrieNode();
    }

    // Insert word into Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Search for prefix
    public List<String> autocomplete(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return new ArrayList<>();
            }
            node = node.children[index];
        }
        List<String> results = new ArrayList<>();
        dfs(node, new StringBuilder(prefix), results);
        return results;
    }

    // DFS to collect all words starting with prefix
    private void dfs(TrieNode node, StringBuilder path, List<String> results) {
        if (node.isEndOfWord) {
            results.add(path.toString());
        }
        for (char c = 'a'; c <= 'z'; c++) {
            int index = c - 'a';
            if (node.children[index] != null) {
                path.append(c);
                dfs(node.children[index], path, results);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        AutocompleteTrie trie = new AutocompleteTrie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("bat");
        trie.insert("ball");

        String prefix = "app";
        List<String> suggestions = trie.autocomplete(prefix);
        System.out.println("Autocomplete for '" + prefix + "': " + suggestions);
    }
}
