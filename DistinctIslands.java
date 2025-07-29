import java.util.HashSet;
import java.util.Set;

public class DistinctIslands {

    public static int numDistinctIslands(int[][] grid) {
        Set<String> shapes = new HashSet<>();
        int rows = grid.length, cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, r, c, path, "o"); // origin
                    shapes.add(path.toString());
                }
            }
        }

        return shapes.size();
    }

    private static void dfs(int[][] grid, int r, int c, StringBuilder path, String dir) {
        int rows = grid.length, cols = grid[0].length;
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 0) return;

        grid[r][c] = 0;
        path.append(dir);

        dfs(grid, r - 1, c, path, "u"); // up
        dfs(grid, r + 1, c, path, "d"); // down
        dfs(grid, r, c - 1, path, "l"); // left
        dfs(grid, r, c + 1, path, "r"); // right

        path.append("b"); // backtrack
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1,1,0,0,0},
            {1,0,0,0,0},
            {0,0,0,1,1},
            {0,0,0,1,1}
        };
        System.out.println("Distinct Islands: " + numDistinctIslands(grid)); // Output: 1
    }
}
