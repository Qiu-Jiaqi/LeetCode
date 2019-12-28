# 5134. 将每个元素替换为右侧最大元素

传送门：[5134. 将每个元素替换为右侧最大元素](https://leetcode-cn.com/contest/biweekly-contest-16/problems/replace-elements-with-greatest-element-on-right-side/)

## 题目描述

给你一个数组 `arr` ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 `-1` 替换。

完成所有替换操作后，请你返回这个数组。

**示例 ：**

```
输入：arr = [17,18,5,4,6,1]
输出：[18,6,6,6,1,-1]
```

**提示：**

- `1 <= arr.length <= 10^4`
- `1 <= arr[i] <= 10^5`

## 分析与代码

分析：从后往前，一次遍历，记录最大值。

代码：

```java
class Solution {
    public int[] replaceElements(int[] arr) {
        int max = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            max = Math.max(max, temp);
        }
        return arr;
    }
}
```



# 5135. 转变数组后最接近目标值的数组和

传送门：[5135. 转变数组后最接近目标值的数组和](https://leetcode-cn.com/contest/biweekly-contest-16/problems/sum-of-mutated-array-closest-to-target/)

## 题目描述

给你一个整数数组 `arr` 和一个目标值 `target` ，请你返回一个整数 `value` ，使得将数组中所有大于 `value` 的值变成 `value` 后，数组的和最接近 `target` （最接近表示两者之差的绝对值最小）。

如果有多种使得和最接近 `target` 的方案，请你返回这些整数中的最小值。

请注意，答案不一定是 `arr` 中的数字。

**示例 1：**

```
输入：arr = [4,9,3], target = 10
输出：3
解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
```

**示例 2：**

```
输入：arr = [2,3,5], target = 10
输出：5
```

**示例 3：**

```
输入：arr = [60864,25176,27249,21296,20204], target = 56803
输出：11361
```

**提示：**

- `1 <= arr.length <= 10^4`
- `1 <= arr[i], target <= 10^5`

## 分析与代码

分析：二分，不断往左收缩，找到计算出的数组和大于等于 target 的最小值。

做的时候边界坑死我了，错了三次。

代码：

```java
class Solution {
    public int findBestValue(int[] arr, int target) {
        int left = 1, right = target;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (cal(arr, mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int cur = cal(arr, left), ll = cal(arr, left - 1);
        int d = Math.abs(cur - target), dl = Math.abs(ll - target);
        return dl <= d ? left - 1 : left;
    }

    public int cal(int[] arr, int value) {
        int sum = 0;
        for (int num : arr) {
            sum += Math.min(num, value);
        }
        return sum;
    }
}
```



# 5153. 层数最深叶子节点的和

传送门：[5153. 层数最深叶子节点的和](https://leetcode-cn.com/contest/biweekly-contest-16/problems/deepest-leaves-sum/)

## 题目描述

给你一棵二叉树，请你返回层数最深的叶子节点的和。

**示例 ：**

```
输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
输出：15
```

**提示：**

- 树中节点数目在 `1` 到 `10^4` 之间。
- 每个节点的值在 `1` 到 `100` 之间。

## 分析与代码

分析：层次遍历。

代码：

```java
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            list.clear();
            for (int i = queue.size(); i >= 1; i--) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        int res = 0;
        for (int num : list) {
            res += num;
        }
        return res;
    }
}
```



# 5137. 最大得分的路径数目

传送门：[5137. 最大得分的路径数目](https://leetcode-cn.com/contest/biweekly-contest-16/problems/number-of-paths-with-max-score/)

## 题目描述

给你一个正方形字符数组 `board` ，你从数组最右下方的字符 `'S'` 出发。

你的目标是到达数组最左上角的字符 `'E'` ，数组剩余的部分为数字字符 `1, 2, ..., 9` 或者障碍 `'X'`。在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。

一条路径的 「得分」 定义为：路径上所有数字的和。

请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对 **`10^9 + 7`** **取余**。

如果没有任何路径可以到达终点，请返回 `[0, 0]` 。

**示例 1：**

```
输入：board = ["E23","2X2","12S"]
输出：[7,1]
```

**示例 2：**

```
输入：board = ["E12","1X1","21S"]
输出：[4,2]
```

**示例 3：**

```
输入：board = ["E11","XXX","11S"]
输出：[0,0]
```

**提示：**

- `2 <= board.length == board[i].length <= 100`

## 分析与代码

分析：没做，有空再看吧，还要复习期末。

代码：

```java

```



# 小结

二分的边界值还是不够熟悉，有时候还是好好总结一下。