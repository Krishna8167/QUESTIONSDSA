import java.util.*;

// A generic tree node
class TreeNode<T> {
    T data;
    List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }
}

// Tree utility class
class GenericTree<T> {
    private TreeNode<T> root;

    public GenericTree(T rootData) {
        root = new TreeNode<>(rootData);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    // Depth-First Search (Recursive)
    public void dfs(TreeNode<T> node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        for (TreeNode<T> child : node.children)
            dfs(child);
    }

    // Breadth-First Search (Level Order)
    public void bfs() {
        if (root == null) return;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            System.out.print(node.data + " ");
            for (TreeNode<T> child : node.children)
                queue.offer(child);
        }
    }

    // Search a node
    public boolean search(TreeNode<T> node, T key) {
        if (node == null) return false;
        if (node.data.equals(key)) return true;
        for (TreeNode<T> child : node.children)
            if (search(child, key)) return true;
        return false;
    }

    // Calculate tree height
    public int height(TreeNode<T> node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (TreeNode<T> child : node.children)
            maxChildHeight = Math.max(maxChildHeight, height(child));
        return 1 + maxChildHeight;
    }

    // Mirror the tree
    public void mirror(TreeNode<T> node) {
        if (node == null) return;
        for (TreeNode<T> child : node.children)
            mirror(child);
        Collections.reverse(node.children);
    }
}

public class GenericTreeDemo {
    public static void main(String[] args) {
        GenericTree<String> tree = new GenericTree<>("A");

        TreeNode<String> root = tree.getRoot();
        TreeNode<String> b = new TreeNode<>("B");
        TreeNode<String> c = new TreeNode<>("C");
        TreeNode<String> d = new TreeNode<>("D");
        root.addChild(b);
        root.addChild(c);
        root.addChild(d);

        b.addChild(new TreeNode<>("E"));
        b.addChild(new TreeNode<>("F"));
        c.addChild(new TreeNode<>("G"));
        d.addChild(new TreeNode<>("H"));
        d.addChild(new TreeNode<>("I"));

        System.out.println("🌳 Depth-First Traversal:");
        tree.dfs(root);

        System.out.println("\n\n🌿 Breadth-First Traversal:");
        tree.bfs();

        System.out.println("\n\n🔍 Searching for node 'G': " + tree.search(root, "G"));
        System.out.println("🔍 Searching for node 'Z': " + tree.search(root, "Z"));

        System.out.println("\n📏 Tree Height: " + tree.height(root));

        System.out.println("\n🔄 Mirroring the tree...");
        tree.mirror(root);

        System.out.println("🌳 DFS after mirror:");
        tree.dfs(root);
    }
}
