// Author: Kris Shar
// Topic: Advanced Hybrid DSA - Graph + Dijkstra + Dynamic Programming (Knapsack) + Greedy Optimization
// Description: A Smart Delivery Route Optimizer that merges multiple algorithmic concepts

import java.util.*;

// Represents a weighted edge between two locations
class Edge {
    String destination;
    int distance;
    Edge(String destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }
}

// Graph class using adjacency list representation
class Graph {
    Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addVertex(String name) {
        adjacencyList.putIfAbsent(name, new ArrayList<>());
    }

    public void addEdge(String src, String dest, int dist, boolean bidirectional) {
        adjacencyList.get(src).add(new Edge(dest, dist));
        if (bidirectional)
            adjacencyList.get(dest).add(new Edge(src, dist));
    }

    // Dijkstra’s algorithm to find the shortest delivery route
    public int shortestDistance(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : adjacencyList.keySet()) distances.put(node, Integer.MAX_VALUE);
        distances.put(start, 0);

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue);
        pq.offer(new AbstractMap.SimpleEntry<>(start, 0));

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> current = pq.poll();
            String node = current.getKey();
            int dist = current.getValue();

            if (dist > distances.get(node)) continue;

            for (Edge edge : adjacencyList.get(node)) {
                int newDist = dist + edge.distance;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    pq.offer(new AbstractMap.SimpleEntry<>(edge.destination, newDist));
                }
            }
        }

        return distances.getOrDefault(end, Integer.MAX_VALUE);
    }
}

// Represents a delivery package
class Package {
    int weight;
    int profit;
    Package(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
    }
}

// Dynamic Programming solution to maximize profit under weight constraint
class PackageOptimizer {
    public static int maximizeProfit(List<Package> packages, int capacity) {
        int n = packages.size();
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            int w = packages.get(i - 1).weight;
            int p = packages.get(i - 1).profit;
            for (int c = 1; c <= capacity; c++) {
                if (w <= c)
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - w] + p);
                else
                    dp[i][c] = dp[i - 1][c];
            }
        }
        return dp[n][capacity];
    }
}

public class SmartDeliverySystem {
    public static void main(String[] args) {
        // Step 1: Create delivery network (Graph)
        Graph graph = new Graph();
        for (String city : List.of("A", "B", "C", "D", "E")) graph.addVertex(city);

        graph.addEdge("A", "B", 4, true);
        graph.addEdge("A", "C", 2, true);
        graph.addEdge("B", "D", 10, true);
        graph.addEdge("C", "E", 3, true);
        graph.addEdge("E", "D", 4, true);
        graph.addEdge("B", "C", 5, true);

        System.out.println("🚚 Smart Delivery System Started...");
        System.out.println("Finding shortest route from A to D...");
        int shortest = graph.shortestDistance("A", "D");
        System.out.println("Shortest route distance: " + shortest + " km");

        // Step 2: Select packages using Knapsack Optimization
        List<Package> packages = List.of(
                new Package(2, 6),    // weight, profit
                new Package(2, 10),
                new Package(3, 12),
                new Package(1, 7)
        );

        int capacity = 5; // truck capacity in weight units
        int maxProfit = PackageOptimizer.maximizeProfit(packages, capacity);

        System.out.println("\nOptimizing packages for profit...");
        System.out.println("Truck capacity: " + capacity + " units");
        System.out.println("Maximum achievable profit: $" + maxProfit);

        // Step 3: Combined Result
        System.out.println("\n✅ Summary:");
        System.out.println("Optimal delivery path (A → D) = " + shortest + " km");
        System.out.println("Max profit with selected packages = $" + maxProfit);
        System.out.println("System merged: Graph + Dijkstra + Dynamic Programming + PriorityQueue 🚀");
    }
}
