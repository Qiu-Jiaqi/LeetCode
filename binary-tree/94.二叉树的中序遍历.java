class Solution {
    private List<Integer> res = new ArrayList<>();

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        // 1. 递归

        // inorder(root);

        // 2. 迭代

        // Stack<TreeNode> stack = new Stack<>();
        // TreeNode cur = root;
        // while (!stack.empty() || cur != null) {
        //     while (cur != null) {
        //         stack.push(cur);
        //         cur = cur.left;
        //     }
        //     res.add(stack.peek().val);
        //     cur = stack.pop().right;
        // }

        // 3. Morris遍历

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}