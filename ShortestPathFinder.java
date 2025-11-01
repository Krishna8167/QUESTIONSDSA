import java.util.*;

// Node class representing an edge in the graph
class Edge {
    int dest;
    int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

// Graph class
class Graph {
    private final Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(int src, int dest, int weight) {
        adjacencyList.putIfAbsent(src, new ArrayList<>());
        adjacencyList.putIfAbsent(dest, new ArrayList<>());
        adjacencyList.get(src).add(new Edge(dest, weight));
        adjacencyList.get(dest).add(new Edge(src, weight)); // undirected
    }

    public void printGraph() {
        System.out.println("\n📘 Graph Representation:");
        for (int node : adjacencyList.keySet()) {
            System.out.print(node + " -> ");
            for (Edge edge : adjacencyList.get(node)) {
                System.out.print("(" + edge.dest + ", w=" + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    public void dijkstra(int source) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int node : adjacencyList.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int cost = current[1];

            if (cost > dist.get(node)) continue;

            for (Edge edge : adjacencyList.get(node)) {
                int newDist = cost + edge.weight;
                if (newDist < dist.get(edge.dest)) {
                    dist.put(edge.dest, newDist);
                    pq.offer(new int[]{edge.dest, newDist});
                }
            }
        }

        System.out.println("\n🗺️ Shortest distances from node " + source + ":");
        for (var entry : dist.entrySet()) {
            System.out.println("Node " + entry.getKey() + " → Distance: " + entry.getValue());
        }
    }
}

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Define edges (src, dest, weight)
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(3, 2, 2);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 5);
        graph.addEdge(4, 5, 3);

        graph.printGraph();

        // Run Dijkstra from node 1
        graph.dijkstra(1);
    }
}
