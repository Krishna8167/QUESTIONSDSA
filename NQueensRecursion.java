public class NQueensRecursion {

    static int N = 8; // You can change this to solve for any N

    // Function to print the board
    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if queen can be placed safely
    public static boolean isSafe(int[][] board, int row, int col) {
        // Check vertical up
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1) return false;

        // Check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;

        // Check upper right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1) return false;

        return true;
    }

    // Recursive function to solve N-Queens
    public static boolean solveNQueens(int[][] board, int row) {
        if (row == N) {
            printBoard(board);
            return true; // return false here to print *all* solutions
        }

        boolean found = false;
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                found = solveNQueens(board, row + 1) || found;
                board[row][col] = 0; // Backtrack
            }
        }

        return found;
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        if (!solveNQueens(board, 0)) {
            System.out.println("No solution exists.");
        }
    }
}
