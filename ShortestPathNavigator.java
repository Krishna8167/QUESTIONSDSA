// Topic: Advanced DSA - Graph + Dijkstra + PriorityQueue + Path Reconstruction
// Description: Finds the shortest path between cities using a weighted graph

import java.util.*;

class Edge {
    String destination;
    int weight;

    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    private final Map<String, List<Edge>> adjList = new HashMap<>();

    public void addEdge(String source, String destination, int weight) {
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(destination, weight));
        adjList.computeIfAbsent(destination, k -> new ArrayList<>()).add(new Edge(source, weight)); // bidirectional
    }

    public List<Edge> getNeighbors(String city) {
        return adjList.getOrDefault(city, new ArrayList<>());
    }

    public Set<String> getCities() {
        return adjList.keySet();
    }
}

class DijkstraResult {
    Map<String, Integer> distance;
    Map<String, String> previous;

    public DijkstraResult(Map<String, Integer> distance, Map<String, String> previous) {
        this.distance = distance;
        this.previous = previous;
    }
}

public class ShortestPathNavigator {

    public static DijkstraResult dijkstra(Graph graph, String start) {
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(Map.Entry.comparingByValue());

        for (String city : graph.getCities()) {
            distance.put(city, Integer.MAX_VALUE);
        }
        distance.put(start, 0);
        pq.offer(new AbstractMap.SimpleEntry<>(start, 0));

        while (!pq.isEmpty()) {
            String current = pq.poll().getKey();

            for (Edge edge : graph.getNeighbors(current)) {
                int newDist = distance.get(current) + edge.weight;
                if (newDist < distance.get(edge.destination)) {
                    distance.put(edge.destination, newDist);
                    previous.put(edge.destination, current);
                    pq.offer(new AbstractMap.SimpleEntry<>(edge.destination, newDist));
                }
            }
        }

        return new DijkstraResult(distance, previous);
    }

    public static List<String> reconstructPath(String start, String end, Map<String, String> previous) {
        LinkedList<String> path = new LinkedList<>();
        String current = end;
        while (current != null && !current.equals(start)) {
            path.addFirst(current);
            current = previous.get(current);
        }
        if (current != null) path.addFirst(start);
        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Create graph (weighted map of cities)
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 5);
        graph.addEdge("B", "D", 10);
        graph.addEdge("C", "E", 3);
        graph.addEdge("E", "D", 4);
        graph.addEdge("D", "F", 11);

        String start = "A";
        String end = "F";

        System.out.println("🗺️  Shortest Path Navigator");
        System.out.println("From: " + start + " → To: " + end);
        System.out.println("--------------------------------");

        DijkstraResult result = dijkstra(graph, start);
        List<String> path = reconstructPath(start, end, result.previous);

        System.out.println("Shortest Path: " + path);
        System.out.println("Total Distance: " + result.distance.get(end));
    }
}
