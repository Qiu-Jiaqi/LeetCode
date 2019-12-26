class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 1. 迭代（1），只加入非空孩子

        // List<List<Integer>> res = new ArrayList<>();
        // Queue<TreeNode> queue = new LinkedList<>();
        // if (root != null) {
        //     queue.offer(root);
        // }
        // while (!queue.isEmpty()) {
        //     List<Integer> level = new ArrayList<>();
        //     for (int i = queue.size(); i > 0; i--) {
        //         TreeNode cur = queue.poll();
        //         level.add(cur.val);
        //         if (cur.left != null) {
        //             queue.offer(cur.left);
        //         }
        //         if (cur.right != null) {
        //             queue.offer(cur.right);
        //         }
        //     }
        //     res.add(level);
        // }

        // 1. 迭代（2），加入所有孩子

        // List<List<Integer>> res = new ArrayList<>();
        // Queue<TreeNode> queue = new LinkedList<>();
        // queue.offer(root);
        // while (!queue.isEmpty()) {
        //     List<Integer> level = new ArrayList<>();
        //     for (int i = queue.size(); i > 0; i--) {
        //         TreeNode cur = queue.poll();
        //         if (cur == null) {
        //             continue;
        //         }
        //         level.add(cur.val);
        //         queue.offer(cur.left);
        //         queue.offer(cur.right);
        //     }
        //     if (level.size() != 0) {
        //         res.add(level);
        //     }
        // }

        // 2. 递归

        levelOrder(root, 0);
        return res;
    }

    public void levelOrder(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        levelOrder(root.left, level + 1);
        levelOrder(root.right, level + 1);
    }
}