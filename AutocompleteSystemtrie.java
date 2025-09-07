import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}

public class AutocompleteSystem {
    private TrieNode root;

    public AutocompleteSystem() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }

    // Get all words with a given prefix
    public List<String> getWordsWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return results; // empty if prefix not found
            }
            node = node.children.get(c);
        }

        dfs(node, new StringBuilder(prefix), results);
        return results;
    }

    // Depth-First Search to collect words
    private void dfs(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix.toString());
        }
        for (char c : node.children.keySet()) {
            dfs(node.children.get(c), prefix.append(c), results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public static void main(String[] args) {
        AutocompleteSystem trie = new AutocompleteSystem();
        trie.insert("cat");
        trie.insert("car");
        trie.insert("cart");
        trie.insert("dog");
        trie.insert("dove");

        System.out.println("Autocomplete for 'ca': " + trie.getWordsWithPrefix("ca"));
        System.out.println("Autocomplete for 'do': " + trie.getWordsWithPrefix("do"));
        System.out.println("Autocomplete for 'z': " + trie.getWordsWithPrefix("z"));
    }
}
