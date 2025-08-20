import java.util.*;

class SocialNetwork {
    private Map<String, List<String>> graph = new HashMap<>();

    // Add user
    public void addUser(String user) {
        graph.putIfAbsent(user, new ArrayList<>());
    }

    // Add friendship (undirected)
    public void addFriendship(String user1, String user2) {
        graph.get(user1).add(user2);
        graph.get(user2).add(user1);
    }

    // Recommend friends for a user
    public List<String> recommendFriends(String user) {
        Set<String> visited = new HashSet<>();
        Map<String, Integer> mutualCount = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        
        visited.add(user);
        queue.offer(user);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String friend : graph.get(current)) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    queue.offer(friend);
                }
                for (String fof : graph.get(friend)) { // friend of friend
                    if (!fof.equals(user) && !graph.get(user).contains(fof)) {
                        mutualCount.put(fof, mutualCount.getOrDefault(fof, 0) + 1);
                    }
                }
            }
        }

        // Priority queue to recommend based on mutual friends (max first)
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(mutualCount.entrySet());

        List<String> recommendations = new ArrayList<>();
        while (!pq.isEmpty()) {
            recommendations.add(pq.poll().getKey());
        }

        return recommendations;
    }
}

public class MultiDSExample {
    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();

        // Add users
        sn.addUser("Alice");
        sn.addUser("Bob");
        sn.addUser("Charlie");
        sn.addUser("David");
        sn.addUser("Eve");

        // Add friendships
        sn.addFriendship("Alice", "Bob");
        sn.addFriendship("Alice", "Charlie");
        sn.addFriendship("Bob", "David");
        sn.addFriendship("Charlie", "Eve");

        // Recommend friends for Alice
        System.out.println("Friend recommendations for Alice: " + sn.recommendFriends("Alice"));
    }
}
