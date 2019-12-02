老实说，抑郁真的影响很大的，头脑越来越不好了。昨晚突然哭了起来，我控制不住自己啊，好痛苦。八点持续到九点多，洗个澡才勉强恢复了一点，最后只做出了两题，但今天做了剩下两题，发现也不难，哈，一看就会，一做就废。

# 5112. 十六进制魔术数字

传送门：[5112. 十六进制魔术数字](https://leetcode-cn.com/contest/biweekly-contest-14/problems/hexspeak/)

## 题目描述

你有一个十进制数字，请按照此规则将它变成「十六进制魔术数字」：首先将它变成字母大写的十六进制字符串，然后将所有的数字 `0` 变成字母 `O` ，将数字 `1` 变成字母 `I` 。

如果一个数字在转换后只包含 `{"A", "B", "C", "D", "E", "F", "I", "O"}` ，那么我们就认为这个转换是有效的。

给你一个字符串 `num` ，它表示一个十进制数 `N`，如果它的十六进制魔术数字转换是有效的，请返回转换后的结果，否则返回 `"ERROR"` 。

<!-- more -->

**示例 1：**  

```
输入：num = "257"
输出："IOI"
解释：257 的十六进制表示是 101 。
```

**示例 2：**  

```
输入：num = "3"
输出："ERROR"
```

**提示:**  

- `1 <= N <= 10^12`
- 给定字符串不会有前导 0 。
- 结果中的所有字母都应该是大写字母。

## 分析与代码

分析：水题，注意 Integer 会溢出（>_<），进制转换都用 BigInteger.toString(int radix) 就好了。

代码：

```java
class Solution {
    public String toHexspeak(String num) {
        String res = new BigInteger(num).toString(16).toUpperCase();
        res = res.replace("1", "I");
        res = res.replace("0", "O");
        for (char ch : res.toCharArray()) {
            if (Character.isDigit(ch)) {
                return "ERROR";
            }
        }
        return res;
    }
}
```



# 5113. 删除区间

传送门：[5113. 删除区间](https://leetcode-cn.com/contest/biweekly-contest-14/problems/remove-interval/)

## 题目描述

给你一个 **有序的** 不相交区间列表 `intervals` 和一个要删除的区间 `toBeRemoved`， `intervals` 中的每一个区间 `intervals[i] = [a, b]` 都表示满足 `a <= x < b` 的所有实数 `x` 的集合。

我们将 `intervals` 中任意区间与 `toBeRemoved` 有交集的部分都删除。

返回删除所有交集区间后， `intervals` 剩余部分的 **有序** 列表。

**示例 1：**

```
输入：intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
输出：[[0,1],[6,7]]
```

**示例 2：**

```
输入：intervals = [[0,5]], toBeRemoved = [2,3]
输出：[[0,2],[3,5]]
```

**提示：**

- `1 <= intervals.length <= 10^4`
- `-10^9 <= intervals[i][0] < intervals[i][1] <= 10^9`

## 分析与代码

分析：五种情况列出来就好，最好画个图，不要乱了，我做的时候就太乱了，已经重写了。

left 表示当前区间左，right 表示当前区间右，removeLeft 表示删除区间左，removeRight 表示删除区间右。

- 当前区间在删除区间的左边或右边，没有相交，这时直接加入，[left, right]
- 当前区间后部分与删除区间相交，这时加入前部分，[left, removeLeft]
- 当前区间前部分与删除区间相交，这时加入后部分，[removeRight, right]
- 当前区间包含删除区间，这时加入前后两端区间，[left, removeLeft] 和 [removeRight, right]

代码：

```java
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > toBeRemoved[1] || interval[1] < toBeRemoved[0]) {
                res.add(Arrays.asList(interval[0], interval[1]));
            } else if (interval[0] < toBeRemoved[0] && interval[1] <= toBeRemoved[1]) {
                res.add(Arrays.asList(interval[0], toBeRemoved[0]));
            } else if (interval[0] >= toBeRemoved[0] && interval[1] > toBeRemoved[1]) {
                res.add(Arrays.asList(toBeRemoved[1], interval[1]));
            } else if (interval[0] < toBeRemoved[0] && interval[1] > toBeRemoved[1]) {
                res.add(Arrays.asList(interval[0], toBeRemoved[0]));
                res.add(Arrays.asList(toBeRemoved[1], interval[1]));
            }
        }
        return res;
    }
}
```



# 5114. 删除树节点

传送门：[5114. 删除树节点](https://leetcode-cn.com/contest/biweekly-contest-14/problems/delete-tree-nodes/)

## 题目描述

给你一棵以节点 0 为根节点的树，定义如下：

- 节点的总数为 `nodes` 个；
- 第 `i` 个节点的值为 `value[i]` ；
- 第 `i` 个节点的父节点是 `parent[i]` 。

请你删除节点值之和为 0 的每一棵子树。

在完成所有删除之后，返回树中剩余节点的数目。

**示例 ：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/30/1421_sample_1.png)

```
输入：nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
输出：2
```

**提示：**

- `1 <= nodes <= 10^4`
- `-10^5 <= value[i] <= 10^5`
- `parent.length == nodes`
- `parent[0] == -1` 表示节点 `0` 是树的根。

## 分析与代码

分析：第一次遍历求每棵子树的节点值之和，用数组保存；第二次遍历记录删除的节点，节点值为 0 的或父节点被标记删除的节点都标记为删除；最后遍历标记数组，计算剩下的节点数。

代码：

```java
class Solution {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        int[] sum = new int[nodes];
        boolean[] delete = new boolean[nodes];
        for (int i = nodes - 1; i >= 0; i--) {
            sum[i] += value[i];
            if (parent[i] != -1) {
                sum[parent[i]] += sum[i];
            }
        }
        for (int i = 0; i < nodes; i++) {
            if (sum[i] == 0 || parent[i] != -1 && delete[parent[i]]) {
                delete[i] = true;
            }
        }
        for (boolean flag : delete) {
            if (flag) {
                nodes--;
            }
        }
        return nodes;
    }
}
```



# 5136. 矩形内船只的数目

传送门：[5136. 矩形内船只的数目](https://leetcode-cn.com/contest/biweekly-contest-14/problems/number-of-ships-in-a-rectangle/)

## 题目描述

*(此题是 **交互式问题** )*

在用笛卡尔坐标系表示的二维海平面上，有一些船。每一艘船都在一个整数点上，且每一个整数点最多只有 1 艘船。

有一个函数 `Sea.hasShips(topRight, bottomLeft)` ，输入参数为右上角和左下角两个点的坐标，当且仅当这两个点所表示的矩形区域（包含边界）内至少有一艘船时，这个函数才返回 `true` ，否则返回 `false` 。

给你矩形的右上角 `topRight` 和左下角 `bottomLeft` 的坐标，请你返回此矩形内船只的数目。题目保证矩形内 **至多只有 10 艘船**。

调用函数 `hasShips` **超过400次** 的提交将被判为 *错误答案（Wrong Answer）* 。同时，任何尝试绕过评测系统的行为都将被取消比赛资格。

**示例 ：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/30/1445_example_1.png)

```
输入：
ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
输出：3
解释：在 [0,0] 到 [4,4] 的范围内总共有 3 艘船。
```

**提示：**

- `ships` 数组只用于评测系统内部初始化。你无法得知 `ships` 的信息，所以只能通过调用 `hasShips` 接口来求解。
- `0 <= bottomLeft[0] <= topRight[0] <= 1000`
- `0 <= bottomLeft[1] <= topRight[1] <= 1000`  

## 分析与代码

分析：分治法，每次分为四个区域，注意边界，主要就是四条边的划分。

递归终止条件为左下和右上为同一个点，每次先判断该区域是否合法，即左下 < 右上，还要判断是否有船，若该区域每船，则不必再递归分治了。

下面代码是把左边和下边划分给了左下区域，故左上区域的纵坐标下限值应为 mid_y + 1，右下区域的横坐标的下限值应为 mid_x + 1；同理，上边划分给左上，右边划分给右下；所以右上区域的左下横纵坐标为 mid_x + 1, mid_y + 1

代码：

```java
 class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        int rightUp_x = topRight[0], rightUp_y = topRight[1];
        int leftDown_x = bottomLeft[0], leftDown_y = bottomLeft[1];
        if (rightUp_x < leftDown_x || rightUp_y < leftDown_y || !sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (rightUp_x == leftDown_x && rightUp_y == leftDown_y) {
            return 1;
        }
        int mid_x = (rightUp_x + leftDown_x) >> 1, mid_y = (rightUp_y + leftDown_y) >>> 1;
        return countShips(sea, new int[] { mid_x, mid_y }, bottomLeft)
                + countShips(sea, topRight, new int[] { mid_x + 1, mid_y + 1 })
                + countShips(sea, new int[] { mid_x, rightUp_y }, new int[] { leftDown_x, mid_y + 1 })
                + countShips(sea, new int[] { rightUp_x, mid_y }, new int[] { mid_x + 1, leftDown_y });
    }
}
```



# 小结

进制转换以后记得用 BigInteger，虽然这次我是用 Long 过的，还用 Integer 错了一次。

