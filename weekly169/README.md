# 5295. 和为零的N个唯一整数

传送门：[5295. 和为零的N个唯一整数](https://leetcode-cn.com/contest/weekly-contest-169/problems/find-n-unique-integers-sum-up-to-zero/)

## 题目描述

给你一个整数 `n`，请你返回 **任意** 一个由 `n` 个 **各不相同** 的整数组成的数组，并且这 `n` 个数相加和为 `0` 。

**示例 1：**

```
输入：n = 5
输出：[-7,-1,1,3,4]
解释：这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
```

**示例 2：**

```
输入：n = 3
输出：[-1,0,1]
```

**示例 3：**

```
输入：n = 1
输出：[0]
```

**提示：**

- `1 <= n <= 1000`

## 分析与代码

分析：每两个两个加入数组就是了，+1，-1，+2，-2，奇偶可以不判断，奇数必定有一个 0，最后落单剩下的一个自然就是 0 了，数组初始化就是 0 了。

代码：

```java
class Solution {
    public int[] sumZero(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n / 2; i++) {
            res[i * 2] = i + 1;
            res[i * 2 + 1] = -(i + 1);
        }
        return res;
    }
}
```



# 5296. 两棵二叉搜索树中的所有元素

传送门：[5296. 两棵二叉搜索树中的所有元素](https://leetcode-cn.com/contest/weekly-contest-169/problems/all-elements-in-two-binary-search-trees/)

## 题目描述

给你 `root1` 和 `root2` 这两棵二叉搜索树。

请你返回一个列表，其中包含 **两棵树** 中的所有整数并按 **升序** 排序。

**示例 1：**

```
输入：root1 = [2,1,4], root2 = [1,0,3]
输出：[0,1,1,2,3,4]
```

**示例 2：**

```
输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
输出：[-10,0,0,1,2,5,7,10]
```

**示例 3：**

```
输入：root1 = [], root2 = [5,1,7,0,2]
输出：[0,1,2,5,7]
```

**示例 4：**

```
输入：root1 = [0,-10,10], root2 = []
输出：[-10,0,10]
```

**示例 5：**

```
输入：root1 = [1,null,8], root2 = [8,1]
输出：[1,1,8,8]
```

**提示：**

- 每棵树最多有 `5000` 个节点。
- 每个节点的值在 `[-10^5, 10^5]` 之间。

## 分析与代码

分析：呃，我直接前序遍历加入所有节点值，然后排序了。

代码：

```java
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        dfs(root1);
        dfs(root2);
        Collections.sort(res);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
```



# 5297. 跳跃游戏 III

传送门：[5297. 跳跃游戏 III](https://leetcode-cn.com/contest/weekly-contest-169/problems/jump-game-iii/)

## 题目描述

这里有一个非负整数数组 `arr`，你最开始位于该数组的起始下标 `start` 处。当你位于下标 `i` 处时，你可以跳到 `i + arr[i]` 或者 `i - arr[i]`。

请你判断自己是否能够跳到对应元素值为 0 的 **任意** 下标处。

注意，不管是什么情况下，你都无法跳到数组之外。

**示例 1：**

```
输入：arr = [4,2,3,0,3,1,2], start = 5
输出：true
解释：
到达值为 0 的下标 3 有以下可能方案： 
下标 5 -> 下标 4 -> 下标 1 -> 下标 3 
下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3 
```

**示例 2：**

```
输入：arr = [4,2,3,0,3,1,2], start = 0
输出：true 
解释：
到达值为 0 的下标 3 有以下可能方案： 
下标 0 -> 下标 4 -> 下标 1 -> 下标 3
```

**示例 3：**

```
输入：arr = [3,0,2,1,2], start = 2
输出：false
解释：无法到达值为 0 的下标 1 处。 
```

**提示：**

- `1 <= arr.length <= 5 * 10^4`
- `0 <= arr[i] < arr.length`
- `0 <= start < arr.length`

## 分析与代码

分析：用一个数组记录该点是否已经跳过，DFS 深度优先遍历一直跳。最后扫描一遍，跳过的点中是否存在值为0.

代码：

```java
class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] ht = new boolean[arr.length];
        dfs(arr, start, ht);
        for (int i = 0; i < arr.length; i++) {
            if (ht[i] && arr[i] == 0) {
                return true;
            }
        }
        return false;
    }

    public void dfs(int[] arr, int cur, boolean[] ht) {
        if (ht[cur]) {
            return;
        }
        ht[cur] = true;
        if (cur + arr[cur] < arr.length) {
            dfs(arr, cur + arr[cur], ht);
        }
        if (cur - arr[cur] >= 0) {
            dfs(arr, cur - arr[cur], ht);
        }
    }
}
```



# 5298. 口算难题

传送门：[5298. 口算难题](https://leetcode-cn.com/contest/weekly-contest-169/problems/verbal-arithmetic-puzzle/)

## 题目描述

给你一个方程，左边用 `words` 表示，右边用 `result` 表示。

你需要根据以下规则检查方程是否可解：

- 每个字符都会被解码成一位数字（0 - 9）。
- 每对不同的字符必须映射到不同的数字。
- 每个 `words[i]` 和 `result` 都会被解码成一个没有前导零的数字。
- 左侧数字之和（`words`）等于右侧数字（`result`）。 

如果方程可解，返回 `True`，否则返回 `False`。

**示例 1：**

```
输入：words = ["SEND","MORE"], result = "MONEY"
输出：true
解释：映射 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
所以 "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
```

**示例 2：**

```
输入：words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
输出：true
解释：映射 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
所以 "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
```

**示例 3：**

```
输入：words = ["THIS","IS","TOO"], result = "FUNNY"
输出：true
```

**示例 4：**

```
输入：words = ["LEET","CODE"], result = "POINT"
输出：false
```

**提示：**

- `2 <= words.length <= 5`
- `1 <= words[i].length, results.length <= 7`
- `words[i], result` 只含有大写英文字母
- 表达式中使用的不同字符数最大为 10

## 分析与代码

分析：没有做。

代码：

```java

```



# 小结

呃，冷静哦，赶紧开始复习啦。