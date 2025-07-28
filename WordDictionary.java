class WordDictionary {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (current.children[idx] == null) {
                current.children[idx] = new TrieNode();
            }
            current = current.children[idx];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (index == word.length()) return node.isWord;

        char ch = word.charAt(index);
        if (ch == '.') {
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            int idx = ch - 'a';
            return node.children[idx] != null && dfs(word, index + 1, node.children[idx]);
        }
    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");

        System.out.println(dict.search("pad")); // false
        System.out.println(dict.search("bad")); // true
        System.out.println(dict.search(".ad")); // true
        System.out.println(dict.search("b..")); // true
    }
}
