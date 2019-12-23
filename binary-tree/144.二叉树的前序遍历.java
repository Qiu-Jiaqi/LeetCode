class Solution {
    private List<Integer> res = new ArrayList<>();

    public void preoder(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preoder(root.left);
        preoder(root.right);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        // 1. 递归

        // preoder(root);

        // 2. 迭代（1），仅适用于根节点先访问，即前序和后序反转

        // Stack<TreeNode> stack = new Stack<>();
        // stack.push(root);
        // while (!stack.empty()) {
        //     TreeNode cur = stack.pop();
        //     if (cur != null) {
        //         res.add(cur.val);
        //         stack.push(cur.right);
        //         stack.push(cur.left);
        //     }
        // }

        // 2. 迭代（2），更通用

        // Stack<TreeNode> stack = new Stack<>();
        // TreeNode cur = root;
        // while (!stack.empty() || cur != null) {
        //     while (cur != null) {
        //         stack.push(cur);
        //         res.add(cur.val);
        //         cur = cur.left;
        //     }
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
                    res.add(cur.val);
                    cur = cur.left;
                } else {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}