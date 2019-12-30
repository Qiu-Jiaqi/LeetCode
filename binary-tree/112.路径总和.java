class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        // 1. 首先想到的是加一个函数传递值，从 0 开始加

        // return hasPathSum(root, sum, 0);

        // 2. 看了官方的题解发现原来可以用减法

        if (root == null) {
            return false;
        }
        sum -= root.val;
        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    public boolean hasPathSum(TreeNode root, int sum, int val) {
        if (root == null) {
            return false;
        }
        val += root.val;
        if (root.left == null && root.right == null) {
            return val == sum;
        }
        return hasPathSum(root.left, sum, val) || hasPathSum(root.right, sum, val);
    }
}