import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
}

class AutocompleteSystem {
    private TrieNode root;
    private Map<String, Integer> frequency;

    public AutocompleteSystem() {
        root = new TrieNode();
        frequency = new HashMap<>();
    }

    // Insert word into Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isWord = true;
        frequency.put(word, frequency.getOrDefault(word, 0) + 1);
    }

    // Get suggestions based on prefix
    public List<String> getSuggestions(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return new ArrayList<>(); // no words with this prefix
            }
            node = node.children.get(ch);
        }

        List<String> results = new ArrayList<>();
        dfs(node, prefix, results);

        // Sort suggestions by frequency using PriorityQueue
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int freqDiff = frequency.get(b) - frequency.get(a);
            if (freqDiff == 0) return a.compareTo(b); // tie → lexicographic
            return freqDiff;
        });

        pq.addAll(results);

        List<String> topSuggestions = new ArrayList<>();
        while (!pq.isEmpty()) {
            topSuggestions.add(pq.poll());
        }
        return topSuggestions;
    }

    // DFS to collect words
    private void dfs(TrieNode node, String word, List<String> results) {
        if (node.isWord) results.add(word);
        for (char ch : node.children.keySet()) {
            dfs(node.children.get(ch), word + ch, results);
        }
    }
}

public class UniqueDSExample {
    public static void main(String[] args) {
        AutocompleteSystem ac = new AutocompleteSystem();

        // Insert words
        ac.insert("dog");
        ac.insert("deer");
        ac.insert("deal");
        ac.insert("dog");
        ac.insert("dogecoin");
        ac.insert("deal");

        // Query
        System.out.println("Suggestions for 'do': " + ac.getSuggestions("do"));
        System.out.println("Suggestions for 'de': " + ac.getSuggestions("de"));
    }
}
