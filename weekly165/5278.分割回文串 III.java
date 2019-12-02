class Solution {
    public int palindromePartition(String s, int k) {
        int[][] dp = new int[s.length() + 1][k + 1];
        for (int[] a : dp) {
            Arrays.fill(a, 101);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int p = 0; p < i; p++) {
                int cache = toPalindrome(s, p, i - 1);
                for (int j = 1; j <= k; j++) {
                    if (j > i) {
                        break;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + cache);
                }
            }
        }
        return dp[s.length()][k];
    }

    public int toPalindrome(String s, int left, int right) {
        int res = 0;
        while (left < right) {
            res += s.charAt(left++) == s.charAt(right--) ? 0 : 1;
        }
        return res;
    }
}