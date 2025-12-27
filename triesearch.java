class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    // Constructor
    TrieNode() {
        children = new TrieNode[26]; // a-z
        isEndOfWord = false;
    }
}

class Trie {

    private TrieNode root;

    // Constructor
    Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';

            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    // Check if any word starts with given prefix
    public boolean startsWith(String prefix) {
        TrieNode current = root;

        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';

            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return true;
    }
}

public class TrieDemo {
    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("app");
        trie.insert("bat");
        trie.insert("ball");

        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("app"));     // true
        System.out.println(trie.search("bat"));     // true
        System.out.println(trie.search("bad"));     // false

        System.out.println(trie.startsWith("ap"));  // true
        System.out.println(trie.startsWith("ba"));  // true
        System.out.println(trie.startsWith("ca"));  // false
    }
}
