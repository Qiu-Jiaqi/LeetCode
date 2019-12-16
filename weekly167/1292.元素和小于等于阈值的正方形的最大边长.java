class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length, m = mat[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = mat[i - 1][j - 1] + dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1];
            }
        }
        for (int k = Math.min(n, m); k >= 1; k--) {
            for (int i = n; i >= 1; i--) {
                if (i < k) {
                    break;
                }
                for (int j = m; j >= 1; j--) {
                    if (j < k) {
                        break;
                    }
                    int cal = dp[i][j] - dp[i][j - k] - dp[i - k][j] + dp[i - k][j - k];
                    if (cal <= threshold) {
                        return k;
                    }
                }
            }
        }
        return 0;
    }
}