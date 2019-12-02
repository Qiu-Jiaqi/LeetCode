class Solution {
    public int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                dp[i][j] = 1;
                if (i == 0 || j == 0) {
                    res++;
                    continue;
                }
                int d = Math.min(dp[i - 1][j], dp[i][j - 1]);
                if (d == 0) {
                    res++;
                    continue;
                }
                dp[i][j] = matrix[i - d][j - d] == 1 ? (d + 1) : d;
                res += dp[i][j];
            }
        }
        return res;
    }
}