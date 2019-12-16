# 1290. 二进制链表转整数

传送门：[1290. 二进制链表转整数](https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/)

## 题目描述

给你一个单链表的引用结点 `head`。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。

请你返回该链表所表示数字的 **十进制值** 。

**示例 1：**

```
输入：head = [1,0,1]
输出：5
解释：二进制数 (101) 转化为十进制数 (5)
```

**示例 2：**

```
输入：head = [0]
输出：0
```

**示例 3：**

```
输入：head = [1]
输出：1
```

**示例 4：**

```
输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
输出：18880
```

**示例 5：**

```
输入：head = [0,0]
输出：0
```

**提示：**

- 链表不为空。
- 链表的结点总数不超过 `30`。
- 每个结点的值不是 `0` 就是 `1`。

## 分析与代码

分析：转为字符串或者直接乘 2 累加都可以。

代码：

```java
/* *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        String s = "";
        while (head != null) {
            s += head.val;
            head = head.next;
        }
        return Integer.parseInt(s, 2);
    }
}
```



# 1291. 顺次数

传送门：[1291. 顺次数](https://leetcode-cn.com/problems/sequential-digits/)

## 题目描述

我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 `1` 的整数。

请你返回由 `[low, high]` 范围内所有顺次数组成的 **有序** 列表（从小到大排序）。

**示例 1：**

```
输出：low = 100, high = 300
输出：[123,234]
```

**示例 2：**

```
输出：low = 1000, high = 13000
输出：[1234,2345,3456,4567,5678,6789,12345]
```

**提示：**

- `10 <= low <= high <= 10^9`

## 分析与代码

分析：36 个列出来就是了。

代码：

```java
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        int[] ht = { 12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345, 3456, 4567, 5678,
                6789, 12345, 23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789, 1234567, 2345678, 3456789,
                12345678, 23456789, 123456789 };
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < ht.length; i++) {
            if (ht[i] > high) {
                break;
            }
            if (ht[i] >= low) {
                res.add(ht[i]);
            }
        }
        return res;
    }
}
```



# 1292. 元素和小于等于阈值的正方形的最大边长

传送门：[1292. 元素和小于等于阈值的正方形的最大边长](https://leetcode-cn.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/)

## 题目描述

给你一个大小为 `m x n` 的矩阵 `mat` 和一个整数阈值 `threshold`。

请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 **0** 。

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/12/15/e1.png)

```
输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
输出：2
解释：总和小于 4 的正方形的最大边长为 2，如图所示。
```

**示例 2：**

```
输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
输出：0
```

**示例 3：**

```
输入：mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
输出：3
```

**示例 4：**

```
输入：mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
输出：2
```

**提示：**

- `1 <= m, n <= 300`
- `m == mat.length`
- `n == mat[i].length`
- `0 <= mat[i][j] <= 10000`
- `0 <= threshold <= 10^5`

## 分析与代码

分析：先求二维前缀和，然后由大到小遍历去找，这样一找到即可返回。

代码：

```java
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length, m = mat[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = mat[i - 1][j - 1] + dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1];
            }
        }
        for (int k = Math.min(n, m); k >= 1; k--) {
            for (int i = n; i >= 1; i--) {
                if (i < k) {
                    break;
                }
                for (int j = m; j >= 1; j--) {
                    if (j < k) {
                        break;
                    }
                    int cal = dp[i][j] - dp[i][j - k] - dp[i - k][j] + dp[i - k][j - k];
                    if (cal <= threshold) {
                        return k;
                    }
                }
            }
        }
        return 0;
    }
}
```



# 1293. 网格中的最短路径

传送门：[1293. 网格中的最短路径](https://leetcode-cn.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/)

## 题目描述

给你一个 `m * n` 的网格，其中每个单元格不是 `0`（空）就是 `1`（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。

如果您 **最多** 可以消除 `k` 个障碍物，请找出从左上角 `(0, 0)` 到右下角 `(m-1, n-1)` 的最短路径，并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1。

**示例 1：**

```
输入： 
grid = 
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]], 
k = 1
输出：6
解释：
不消除任何障碍的最短路径是 10。
消除位置 (3,2) 处的障碍后，最短路径是 6 。该路径是 (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
```

**示例 2：**

```
输入：
grid = 
[[0,1,1],
 [1,1,1],
 [1,0,0]], 
k = 1
输出：-1
解释：
我们至少需要消除两个障碍才能找到这样的路径。
```

**提示：**

- `grid.length == m`
- `grid[0].length == n`
- `1 <= m, n <= 40`
- `1 <= k <= m*n`
- `grid[i][j] == 0 **or** 1`
- `grid[0][0] == grid[m-1][n-1] == 0`

## 分析与代码

分析：先放着。

代码：

```java
class Solution {
    class Node {
        int row;
        int col;
        int oneCount; // bfs过程中某一条路径到达该节点时的路径上的1的个数
        int pathLen; // bfs过程中到达某一节点的路径的长度（即走过的步数）

        Node(int row, int col, int oneCount, int pathLen) {
            this.row = row;
            this.col = col;
            this.oneCount = oneCount;
            this.pathLen = pathLen;
        }
    }

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        LinkedList<Node> queue = new LinkedList<>();
        Node rootNode = new Node(0, 0, 0, 0);
        queue.add(rootNode);
        Node[][] visited = new Node[m][n];
        visited[0][0] = rootNode;
        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };
        while (!queue.isEmpty()) {
            List<Node> nodeList = new ArrayList<>();
            while (!queue.isEmpty()) {
                nodeList.add(queue.removeFirst());
            }
            for (Node node : nodeList) {
                int row = node.row;
                int col = node.col;
                int oneCount = node.oneCount;
                int pathLen = node.pathLen;
                // 通过bfs到达终点，那么就是最短路径
                if (row == m - 1 && col == n - 1) {
                    return pathLen;
                }
                // 上下左右四个方向
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dx[i];
                    int nextCol = col + dy[i];
                    if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                        continue;
                    }
                    int curOneCount = grid[nextRow][nextCol] == 1 ? oneCount + 1 : oneCount;
                    if (curOneCount <= k) { // 这里判断是关键，也就是说进入队列的路径上的1的个数都必须<=k，这样就能消除
                        if (visited[nextRow][nextCol] == null || curOneCount < visited[nextRow][nextCol].oneCount) {
                            // curOneCount < visited[nextRow][nextCol].oneCount
                            // 这个判断条件是只有新路径的1的个数小于旧路径1的个数的时候，才需要加入到队列中。
                            Node nextNode = new Node(nextRow, nextCol, curOneCount, pathLen + 1);
                            queue.add(nextNode);
                            visited[nextRow][nextCol] = nextNode;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
```
