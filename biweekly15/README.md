# 5126. 有序数组中出现次数超过25%的元素

传送门：[5126. 有序数组中出现次数超过25%的元素](https://leetcode-cn.com/problems/element-appearing-more-than-25-in-sorted-array/)

## 题目描述

给你一个非递减的 **有序** 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。

请你找到并返回这个整数

**示例 ：**

```
输入：arr = [1,2,2,6,6,6,6,7,10]
输出：6
```

**提示：**

- `1 <= arr.length <= 10^4`
- `0 <= arr[i] <= 10^5`

## 分析与代码

分析：这题我觉得我的代码还是超级简洁的。

代码：

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        for (int count = 1, i = 1; i < arr.length; i++) {
            count = arr[i] == arr[i - 1] ? count + 1 : 1;
            if (count > arr.length / 4) {
                return arr[i];
            }
        }
        return arr[0];
    }
}
```



# 5127. 删除被覆盖区间

传送门：[5127. 删除被覆盖区间](https://leetcode-cn.com/problems/remove-covered-intervals/)

## 题目描述

给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。

只有当 `c <= a` 且 `b <= d` 时，我们才认为区间 `[a,b)` 被区间 `[c,d)` 覆盖。

在完成所有删除操作后，请你返回列表中剩余区间的数目。

**示例 ：**

```
输入：intervals = [[1,4],[3,6],[2,8]]
输出：2
解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
```

**提示：**

- `1 <= intervals.length <= 1000`
- `0 <= intervals[i][0] < intervals[i][1] <= 10^5`
- 对于所有的 `i != j`：`intervals[i] != intervals[j]`

## 分析与代码

分析：先按左边界排序，然后就是遍历了，每个区间都与其后面的区间比较，只需要比较右边界值即可，用一个 boolean 数组记录已经删除的区间。

呃，感觉前几周做过类似的题呢。

代码：

```java
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        boolean[] flag = new boolean[intervals.length];
        int res = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i][1] >= intervals[j][1] && !flag[j]) {
                    res--;
                    flag[j] = true;
                }
            }
        }
        return res;
    }
}
```



# 5123. 字母组合迭代器

传送门：[5123. 字母组合迭代器](https://leetcode-cn.com/problems/iterator-for-combination/)

## 题目描述

请你设计一个迭代器类，包括以下内容：

- 一个构造函数，输入参数包括：一个 **有序且字符唯一** 的字符串 `characters`（该字符串只包含小写英文字母）和一个数字 `combinationLength` 。
- 函数 *next()* ，按 **字典序** 返回长度为 `combinationLength` 的下一个字母组合。
- 函数 *hasNext()* ，只有存在长度为 `combinationLength` 的下一个字母组合时，才返回 `True`；否则，返回 `False`。

**示例 ：**

```
CombinationIterator iterator = new CombinationIterator("abc", 2); // 创建迭代器 iterator

iterator.next(); // 返回 "ab"
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 "ac"
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 "bc"
iterator.hasNext(); // 返回 false
```

**提示：**

- `1 <= combinationLength <= characters.length <= 15`
- 每组测试数据最多包含 `10^4` 次函数调用。
- 题目保证每次调用函数 `next` 时都存在下一个字母组合。

## 分析与代码

分析：先回溯构造好，然后调用就是了。

代码：

```java
class CombinationIterator {
    String s;
    List<String> cache = new ArrayList<>();
    int num = 0;

    public CombinationIterator(String characters, int combinationLength) {
        s = characters;
        backtrack("", combinationLength, 0);
    }

    public void backtrack(String cur, int combinationLength, int index) {
        if (cur.length() == combinationLength) {
            cache.add(cur);
            return;
        }
        for (int i = index; i < s.length(); i++) {
            backtrack(cur + s.charAt(i), combinationLength, i + 1);
        }
    }

    public String next() {
        return cache.get(num++);
    }

    public boolean hasNext() {
        return num < cache.size();
    }
}
```



# 5129. 下降路径最小和  II

传送门：[5129. 下降路径最小和  II](https://leetcode-cn.com/problems/minimum-falling-path-sum-ii/)

## 题目描述

给你一个整数方阵 `arr` ，定义「非零偏移下降路径」为：从 `arr` 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。

请你返回非零偏移下降路径数字和的最小值。

**示例 1：**

```
输入：arr = [[1,2,3],[4,5,6],[7,8,9]]
输出：13
解释：
所有非零偏移下降路径包括：
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
下降路径中数字和最小的是 [1,5,7] ，所以答案是 13 。
```

**提示：**

- `1 <= arr.length == arr[i].length <= 200`
- `-99 <= arr[i][j] <= 99`

## 分析与代码

分析：这题，，，动态规划，$dp[i][j]$表示当前路径走到第 i 行第 j 列的最小路径和，第 0 行置为初始 arr[0] 的值。

转移方程为 $dp[i][j] = min(dp[i][j],dp[i-1][k] + arr[i][j]),(k != j)$，这里我昨晚把方程写错了，写成了$dp[i-1][j] + arr[i][k]$

最后就算了，就两个字母反了啊，不然就是第一次完全靠自己做出四道题了。

想想也是，当前路径不可能由上一行同一列的加出来呀，不可能是$dp[i-1][j]$的，不知道昨晚发什么傻。

代码：

```java
class Solution {
    public int minFallingPathSum(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr[0].length; i++) {
            dp[0][i] = arr[0][i];
        }
        for (int i = 1; i < arr.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    if (j == k) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + arr[i][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < arr[0].length; i++) {
            res = Math.min(res, dp[arr.length - 1][i]);
        }
        return res;
    }
}
```



# 小结

本来差点就能完全靠自己写出四题，可惜了啊，这次双周赛好简单的，好像双周赛都简单一些。

