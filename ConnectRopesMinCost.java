import java.util.PriorityQueue;

public class ConnectRopesMinCost {
    
    public static int minCost(int[] ropes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Add all ropes to min-heap
        for (int rope : ropes) {
            pq.add(rope);
        }

        int totalCost = 0;

        // Keep connecting the two smallest ropes
        while (pq.size() > 1) {
            int first = pq.poll();   // smallest
            int second = pq.poll();  // second smallest
            int cost = first + second;
            totalCost += cost;
            pq.add(cost); // push back the combined rope
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[] ropes = {4, 3, 2, 6};
        System.out.println("Minimum cost to connect ropes: " + minCost(ropes));  // Output: 29
    }
}
