class Solution {
    private List<Integer> res = new ArrayList<>();

    public void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        res.add(root.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        // 1. 递归

        // postorder(root);

        // 2. 迭代（1），根右左，反转为左右根

        // Stack<TreeNode> stack = new Stack<>();
        // stack.push(root);
        // while (!stack.empty()) {
        //     TreeNode cur = stack.pop();
        //     if (cur != null) {
        //         res.add(0, cur.val);
        //         stack.push(cur.left);
        //         stack.push(cur.right);
        //     }
        // }

        // 2. 迭代（2），根右左，反转为左右根

        // Stack<TreeNode> stack = new Stack<>();
        // TreeNode cur = root;
        // while (!stack.empty() || cur != null) {
        //     while (cur != null) {
        //         stack.push(cur);
        //         res.add(0, cur.val);
        //         cur = cur.right;
        //     }
        //     cur = stack.pop().left;
        // }

        // 3. 莫里斯遍历（Morris Traversal）

        TreeNode cur = new TreeNode(-1);
        cur.left = root;
        while (cur != null) {
            if (cur.left == null) {
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
                    res.addAll(reverseAdd(cur.left, prev));
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    public List<Integer> reverseAdd(TreeNode from, TreeNode to) {
        List<Integer> temp = new ArrayList<>();
        while (from != to) {
            temp.add(0, from.val);
            from = from.right;
        }
        temp.add(0, to.val);
        return temp;
    }
}