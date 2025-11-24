import java.util.*;

// Tree Node class
class TreeNode {
    String name;                           // Node name
    Map<String, String> dictionary;        // Dictionary inside each node
    List<TreeNode> children;               // Children (tree structure)

    public TreeNode(String name) {
        this.name = name;
        this.dictionary = new HashMap<>();
        this.children = new ArrayList<>();
    }

    // Add a key–value pair to the dictionary
    public void addToDictionary(String key, String value) {
        dictionary.put(key, value);
    }

    // Add a child node
    public void addChild(TreeNode child) {
        children.add(child);
    }

    // Print the tree recursively
    public void printTree(String indent) {
        System.out.println(indent + "Node: " + name);
        System.out.println(indent + "  Dictionary: " + dictionary);

        for (TreeNode child : children) {
            child.printTree(indent + "  ");
        }
    }
}


public class TreeDictionaryDemo {
    public static void main(String[] args) {

        // Root node
        TreeNode root = new TreeNode("Root");
        root.addToDictionary("creator", "Admin");
        root.addToDictionary("version", "1.0");

        // Child node 1
        TreeNode child1 = new TreeNode("Child1");
        child1.addToDictionary("color", "blue");
        child1.addToDictionary("size", "large");

        // Child node 2
        TreeNode child2 = new TreeNode("Child2");
        child2.addToDictionary("priority", "high");

        // Sub-child under Child1
        TreeNode subChild = new TreeNode("SubChild");
        subChild.addToDictionary("status", "active");

        // Build the tree
        child1.addChild(subChild);
        root.addChild(child1);
        root.addChild(child2);

        // Print the entire tree
        root.printTree("");
    }
}
