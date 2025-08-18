import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
    int prefixCount; // keeps track of how many words share this prefix
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
            node.prefixCount++; // increase count for every prefix
        }
        node.isEndOfWord = true;
    }

    // Count words starting with a given prefix
    public int countWordsWithPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return 0;
            }
            node = node.children.get(ch);
        }
        return node.prefixCount;
    }
}

public class PrefixCounter {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apricot");
        trie.insert("banana");

        String prefix = "ap";
        System.out.println("Number of words starting with '" + prefix + "': " 
                           + trie.countWordsWithPrefix(prefix));
    }
}
