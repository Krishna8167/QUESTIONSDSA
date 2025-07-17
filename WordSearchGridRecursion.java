public class WordSearchGridRecursion {

    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        // Try starting DFS from every cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, i, j, word, 0)) return true;
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, int row, int col, String word, int index) {
        if (index == word.length()) return true;

        // Boundary and character check
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length 
            || board[row][col] != word.charAt(index)) return false;

        char temp = board[row][col];
        board[row][col] = '#';  // mark as visited

        boolean found = dfs(board, row + 1, col, word, index + 1) ||
                        dfs(board, row - 1, col, word, index + 1) ||
                        dfs(board, row, col + 1, word, index + 1) ||
                        dfs(board, row, col - 1, word, index + 1);

        board[row][col] = temp; // unmark

        return found;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "ABCCED";
        System.out.println("Does the word \"" + word + "\" exist in board? " + exist(board, word));
    }
}
