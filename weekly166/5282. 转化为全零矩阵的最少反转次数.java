class Solution {
    // bit的每一位代表每个格子是否被选择，n*m的矩阵压成一个bit，这里的n在题目中表示n*m
    private int count(int bit, int n) {
        int ret = 0;
        for (int i = 0;i < n;i++) {
            if ((bit & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }
    // 方向数组，注意最后的0,0，因为自身也有可能被选择
    private final int[][] d = {{0,1},{1,0},{0,-1},{-1,0},{0,0}};
    // 判断选择串bit是否能满足题目要求
    boolean judge(int[][] mat, int bit) {
        for (int i = 0;i < mat.length;i++) {
            for (int j = 0;j < mat[0].length;j++) {
                int sum = mat[i][j];
                for (int k = 0;k < d.length;k++) {
                    int dx = i + d[k][0];
                    int dy = j + d[k][1];
                    
                    if (dx >= 0 && dx < mat.length && dy >= 0 && dy < mat[0].length) {
                        int t = dx * mat[0].length + dy;
                        if ((bit & (1 << t)) != 0) {
                            sum++;
                        }
                    }
                }
                // 如果sum是奇数，说明经过bit转换后，mat[i][j]变成了1
                if (sum % 2 != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public int minFlips(int[][] mat) {
        int n = mat.length * mat[0].length;
        int ret = 100;
        for (int i = 0;i < (1 << n);i++) {
            if (judge(mat, i)) {
                ret = Math.min(ret, count(i, n));
            }
        }
        if (ret == 100) {
            return -1;
        }
        return ret;
    }
}

作者：小白二号
链接：https://leetcode-cn.com/circle/discuss/FpOkuW/view/Oa59qT/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。