import java.util.*;

public class LargestRectangleInHistogram {

    // Function to compute the largest rectangle area
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }
        return maxArea;
    }

    // Main method to test
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("📊 Enter number of bars in histogram:");
        int n = sc.nextInt();

        int[] heights = new int[n];
        System.out.println("📥 Enter the heights of each bar:");
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        int result = largestRectangleArea(heights);
        System.out.println("\n✅ Largest rectangular area in histogram: " + result);
    }
}
