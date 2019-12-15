class Solution {
    public int minFallingPathSum(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr[0].length; i++) {
            dp[0][i] = arr[0][i];
        }
        for (int i = 1; i < arr.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    if (j == k) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + arr[i][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < arr[0].length; i++) {
            res = Math.min(res, dp[arr.length - 1][i]);
        }
        return res;
    }
}