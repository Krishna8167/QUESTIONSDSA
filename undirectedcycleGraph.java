import java.util.*;

class DisjointSet {
    int[] parent, rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // each node is its own parent initially
            rank[i] = 0;
        }
    }

    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union by rank
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // cycle detected
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

public class GraphCycleDetection {
    public static void main(String[] args) {
        int V = 4; // number of vertices
        int[][] edges = { {0, 1}, {1, 2}, {2, 3}, {3, 0} }; // last edge creates a cycle

        DisjointSet ds = new DisjointSet(V);
        boolean cycle = false;

        for (int[] edge : edges) {
            if (!ds.union(edge[0], edge[1])) {
                cycle = true;
                break;
            }
        }

        if (cycle) {
            System.out.println("Graph contains a cycle.");
        } else {
            System.out.println("Graph does not contain a cycle.");
        }
    }
}
