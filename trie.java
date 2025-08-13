import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }

    public List<String> autocomplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return results; // No matches
            }
            current = current.children.get(c);
        }

        findAllWords(current, prefix, results);
        return results;
    }

    private void findAllWords(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix);
        }
        for (char c : node.children.keySet()) {
            findAllWords(node.children.get(c), prefix + c, results);
        }
    }
}

public class AutocompleteTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("apt");
        trie.insert("bat");
        trie.insert("ball");
        trie.insert("banana");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a prefix to search: ");
        String prefix = sc.nextLine();

        List<String> results = trie.autocomplete(prefix);
        if (results.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Words starting with \"" + prefix + "\": " + results);
        }
        sc.close();
    }
}
