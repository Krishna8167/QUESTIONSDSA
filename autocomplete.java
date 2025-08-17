import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert word into Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        node.isEndOfWord = true;
    }

    // Get suggestions for a prefix
    public List<String> autoComplete(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return Collections.emptyList();
            }
            node = node.children.get(ch);
        }
        List<String> results = new ArrayList<>();
        dfs(node, new StringBuilder(prefix), results);
        return results;
    }

    // DFS to collect words
    private void dfs(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix.toString());
        }
        for (char ch : node.children.keySet()) {
            prefix.append(ch);
            dfs(node.children.get(ch), prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}

public class AutoCompleteSystem {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("dog");
        trie.insert("deer");
        trie.insert("deal");
        trie.insert("dove");

        String prefix = "de";
        System.out.println("Suggestions for prefix '" + prefix + "': " + trie.autoComplete(prefix));
    }
}
