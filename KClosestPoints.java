import java.util.*;

public class KClosestPoints {

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(distance(b), distance(a)) // max-heap based on distance
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove farthest point
            }
        }

        int[][] result = new int[k][2];
        int i = 0;
        for (int[] point : maxHeap) {
            result[i++] = point;
        }
        return result;
    }

    private static int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1]; // No need for sqrt
    }

    public static void main(String[] args) {
        int[][] points = { {3, 3}, {5, -1}, {-2, 4} };
        int k = 2;
        int[][] closest = kClosest(points, k);

        System.out.println("K closest points to origin:");
        for (int[] p : closest) {
            System.out.println(Arrays.toString(p));
        }
    }
}
