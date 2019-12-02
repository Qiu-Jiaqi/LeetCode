class Solution {
    public String tictactoe(int[][] moves) {
        int[][] board = new int[3][3];
        for (int i = 0; i < moves.length; i++) {
            int row = moves[i][0], col = moves[i][1];
            if (i % 2 == 0) {
                board[row][col] = 1;
                if (judge(board, row, col)) {
                    return "A";
                }
            } else {
                board[row][col] = -1;
                if (judge(board, row, col)) {
                    return "B";
                }
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    public boolean judge(int[][] board, int row, int col) {
        if (Math.abs(board[row][0] + board[row][1] + board[row][2]) == 3) {
            return true;
        }
        if (Math.abs(board[0][col] + board[1][col] + board[2][col]) == 3) {
            return true;
        }
        if (row - col == 0) {
            if (Math.abs(board[0][0] + board[1][1] + board[2][2]) == 3) {
                return true;
            }
        }
        if (row + col == 2) {
            if (Math.abs(board[0][2] + board[1][1] + board[2][0]) == 3) {
                return true;
            }
        }
        return false;
    }
}