// Author: Kris Shar
// Topic: Advanced DSA - Graph + PriorityQueue + Dijkstra’s Algorithm
// Description: Finds the shortest path from a source node to all other nodes in a weighted graph

import java.util.*;

class Edge {
    String target;
    int weight;

    Edge(String target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

class Graph {
    private final Map<String, List<Edge>> adjacencyList;

    Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add vertex
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Add edge (bidirectional or unidirectional)
    public void addEdge(String source, String target, int weight, boolean bidirectional) {
        adjacencyList.get(source).add(new Edge(target, weight));
        if (bidirectional) {
            adjacencyList.get(target).add(new Edge(source, weight));
        }
    }

    // Dijkstra’s shortest path
    public Map<String, Integer> dijkstra(String start) {
        // Distance map
        Map<String, Integer> distances = new HashMap<>();
        for (String vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        // Min-heap based on shortest distance
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue);
        pq.offer(new AbstractMap.SimpleEntry<>(start, 0));

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> current = pq.poll();
            String currentVertex = current.getKey();
            int currentDist = current.getValue();

            if (currentDist > distances.get(currentVertex)) continue;

            for (Edge edge : adjacencyList.get(currentVertex)) {
                int newDist = currentDist + edge.weight;
                if (newDist < distances.get(edge.target)) {
                    distances.put(edge.target, newDist);
                    pq.offer(new AbstractMap.SimpleEntry<>(edge.target, newDist));
                }
            }
        }

        return distances;
    }

    public void printGraph() {
        for (Map.Entry<String, List<Edge>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Edge edge : entry.getValue()) {
                System.out.print("(" + edge.target + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }
}

public class DijkstraShortestPath {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Adding vertices
        String[] vertices = {"A", "B", "C", "D", "E"};
        for (String v : vertices) graph.addVertex(v);

        // Adding weighted edges
        graph.addEdge("A", "B", 4, true);
        graph.addEdge("A", "C", 2, true);
        graph.addEdge("B", "C", 5, true);
        graph.addEdge("B", "D", 10, true);
        graph.addEdge("C", "E", 3, true);
        graph.addEdge("E", "D", 4, true);
        graph.addEdge("D", "A", 7, true);

        System.out.println("Graph Representation:");
        graph.printGraph();

        System.out.println("\nShortest Paths from A:");
        Map<String, Integer> shortestPaths = graph.dijkstra("A");
        for (Map.Entry<String, Integer> entry : shortestPaths.entrySet()) {
            System.out.println("Distance from A to " + entry.getKey() + " = " + entry.getValue());
        }
    }
}
