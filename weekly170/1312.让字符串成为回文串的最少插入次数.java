class Solution {
    char[] sc;
    int[][] dp;

    public int backtrack(int start, int end) {
        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != -1) {
            return dp[start][end];
        }
        if (sc[start] == sc[end]) {
            dp[start][end] = backtrack(start + 1, end - 1);
        } else {
            dp[start + 1][end] = backtrack(start + 1, end);
            dp[start][end - 1] = backtrack(start, end - 1);
            dp[start][end] = Math.min(dp[start + 1][end], dp[start][end - 1]) + 1;
        }
        return dp[start][end];
    }

    public int minInsertions(String s) {
        // 1. 记忆化递归
        sc = s.toCharArray();
        dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return backtrack(0, s.length() - 1);

        // 2. 动态规划
        dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][s.length() - 1];

        // 3. 转为最长公共子序列问题
        String t = new StringBuilder(s).reverse().toString();
        return s.length() - LCS(s, t);
    }

    public int LCS(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}