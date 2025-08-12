import java.util.*;

public class KClosestPointsHeap {

    static class Point {
        int x, y;
        double distance;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = Math.sqrt(x * x + y * y);
        }
    }

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(
            (a, b) -> Double.compare(b.distance, a.distance) // Max Heap
        );

        for (int[] p : points) {
            Point point = new Point(p[0], p[1]);
            maxHeap.add(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove farthest
            }
        }

        int[][] result = new int[k][2];
        int idx = 0;
        while (!maxHeap.isEmpty()) {
            Point p = maxHeap.poll();
            result[idx++] = new int[]{p.x, p.y};
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] points = { {1, 3}, {-2, 2}, {5, 8}, {0, 1} };
        int k = 2;

        int[][] closest = kClosest(points, k);
        System.out.println("K Closest Points to Origin:");
        for (int[] p : closest) {
            System.out.println(Arrays.toString(p));
        }
    }
}
