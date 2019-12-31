class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][] dpSum = new int[n + 1][n + 1], dpPath = new int[n + 1][n + 1];
        dpPath[n - 1][n - 1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            char[] cur = board.get(i).toCharArray();
            if (i == 0 || i == n - 1) {
                cur[i] = '0';
            }
            for (int j = n - 1; j >= 0; j--) {
                if (cur[j] == 'X') {
                    continue;
                }
                int max = Math.max(dpSum[i + 1][j + 1], Math.max(dpSum[i][j + 1], dpSum[i + 1][j]));
                if (dpSum[i + 1][j + 1] == max) {
                    dpPath[i][j] = (dpPath[i][j] + dpPath[i + 1][j + 1]) % 1000000007;
                }
                if (dpSum[i][j + 1] == max) {
                    dpPath[i][j] = (dpPath[i][j] + dpPath[i][j + 1]) % 1000000007;
                }
                if (dpSum[i + 1][j] == max) {
                    dpPath[i][j] = (dpPath[i][j] + dpPath[i + 1][j]) % 1000000007;
                }
                dpSum[i][j] = max + (cur[j] - '0');
            }
        }
        return dpPath[0][0] > 0 ? new int[] { dpSum[0][0], dpPath[0][0] } : new int[] { 0, 0 };
    }
}