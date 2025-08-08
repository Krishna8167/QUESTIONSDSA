import java.util.PriorityQueue;

public class MinCostToConnectRopes {

    public static int minCost(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all ropes to the heap
        for (int rope : ropes) {
            minHeap.add(rope);
        }

        int cost = 0;

        // Keep connecting until only one rope remains
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            int newRope = first + second;
            cost += newRope;
            minHeap.add(newRope);
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] ropes = {4, 3, 2, 6};
        System.out.println("Minimum cost to connect ropes: " + minCost(ropes));
    }
}
