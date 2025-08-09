import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    static class Pair {
        int row, col;
        Pair(int r, int c) { row = r; col = c; }
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    bfs(grid, visited, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(char[][] grid, boolean[][] visited, int row, int col) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        visited[row][col] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p.row + dr[i];
                int nc = p.col + dc[i];
                if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length &&
                    grid[nr][nc] == '1' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Pair(nr, nc));
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '1'},
            {'0', '0', '0', '1', '1'},
            {'0', '0', '0', '0', '0'},
            {'1', '0', '1', '0', '1'}
        };
        System.out.println("Number of islands: " + numIslands(grid));
    }
}
