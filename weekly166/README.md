哇哦，今天才做了一道题呢，起得太晚了，要去吃饭。

# 5279. 整数的各位积和之差

传送门：[5279. 整数的各位积和之差](https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/)

## 题目描述

给你一个整数 `n`，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。	

**示例 1：**

```
输入：n = 234
输出：15 
解释：
各位数之积 = 2 * 3 * 4 = 24 
各位数之和 = 2 + 3 + 4 = 9 
结果 = 24 - 9 = 15
```

**示例 2：**

```
输入：n = 4421
输出：21
解释： 
各位数之积 = 4 * 4 * 2 * 1 = 32 
各位数之和 = 4 + 4 + 2 + 1 = 11 
结果 = 32 - 11 = 21
```

**提示：**

- `1 <= n <= 10^5`

## 分析与代码

分析：超级简单啦。

代码：

```java
class Solution {
    public int subtractProductAndSum(int n) {
        int pro = 1, sum = 0;
        while (n != 0) {
            int r = n % 10;
            pro *= r;
            sum += r;
            n /= 10;
        }
        return pro - sum;
    }
}
```



# 5280. 用户分组

传送门：[5280. 用户分组](https://leetcode-cn.com/problems/group-the-people-given-the-group-size-they-belong-to/)

## 题目描述

有 `n` 位用户参加活动，他们的 **ID** 从 `0` 到 `n - 1`，每位用户都 **恰好** 属于某一用户组。给你一个长度为 `n` 的数组 `groupSizes`，其中包含每位用户所处的用户组的大小，请你返回用户分组情况（存在的用户组以及每个组中用户的 ID）。

你可以任何顺序返回解决方案，ID 的顺序也不受限制。此外，题目给出的数据保证至少存在一种解决方案。

**示例 1：**

```
输入：groupSizes = [3,3,3,3,3,1,3]
输出：[[5],[0,1,2],[3,4,6]]
解释： 
其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
```

**示例 2：**

```
输入：groupSizes = [2,1,3,3,3,2]
输出：[[1],[0,5],[2,3,4]]
```

**提示：**

- `groupSizes.length == n`
- `1 <= n <= 500`
- `1 <= groupSizes[i] <= n`

## 分析与代码

分析：也是暴力就完事了。

代码：

```java
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer>[] groupList = new List[groupSizes.length + 1];
        for (int i = 0; i < groupList.length; i++) {
            groupList[i] = new ArrayList<>();
        }
        for (int i = 0; i < groupSizes.length; i++) {
            groupList[groupSizes[i]].add(i);
        }
        for (int val = 1; val < groupList.length; val++) {
            for (int index = 0; index < groupList[val].size(); index += val) {
                res.add(groupList[val].subList(index, Math.min(index + val, groupList[val].size())));
            }
        }
        return res;
    }
}
```



# 5281. 使结果不超过阈值的最小除数

传送门：[5281. 使结果不超过阈值的最小除数](https://leetcode-cn.com/problems/find-the-smallest-divisor-given-a-threshold/)

## 题目描述

给你一个整数数组 `nums` 和一个正整数 `threshold` ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。

请你找出能够使上述结果小于等于阈值 `threshold` 的除数中 **最小** 的那个。

每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。

题目保证一定有解。

**示例 1：**

```
输入：nums = [1,2,5,9], threshold = 6
输出：5
解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
```

**示例 2：**

```
输入：nums = [2,3,5,7,11], threshold = 11
输出：3
```

**示例 3：**

```
输入：nums = [19], threshold = 5
输出：4
```

**提示：**

- `1 <= nums.length <= 5 * 10^4`
- `1 <= nums[i] <= 10^6`
- `nums.length <= threshold <= 10^6`  

## 分析与代码

分析：二分不断往左收缩。

代码：

```java
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 0, right = 1000000;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (judge(nums, threshold, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean judge(int[] nums, int threshold, double div) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += Math.ceil(nums[i] / div);
            if (sum > threshold) {
                return false;
            }
        }
        return true;
    }
}
```



# 5282. 转化为全零矩阵的最少反转次数

传送门：[5282. 转化为全零矩阵的最少反转次数](https://leetcode-cn.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/)

## 题目描述

给你一个 `m x n` 的二进制矩阵 `mat`。

每一步，你可以选择一个单元格并将它反转（反转表示 0 变 1 ，1 变 0 ）。如果存在和它相邻的单元格，那么这些相邻的单元格也会被反转。（注：相邻的两个单元格共享同一条边。）

请你返回将矩阵 `mat` 转化为全零矩阵的*最少反转次数*，如果无法转化为全零矩阵，请返回 **-1** 。

二进制矩阵的每一个格子要么是 0 要么是 1 。

全零矩阵是所有格子都为 0 的矩阵

**示例 1：**

```
输入：mat = [[0,0],[0,1]]
输出：3
解释：一个可能的解是反转 (1, 0)，然后 (0, 1) ，最后是 (1, 1) 。
```

**示例 2：**

```
输入：mat = [[0]]
输出：0
解释：给出的矩阵是全零矩阵，所以你不需要改变它。
```

**示例 3：**

```
输入：mat = [[1,1,1],[1,0,1],[0,0,0]]
输出：6
```

**示例 4：**

```
输入：mat = [[1,0,0],[1,0,0]]
输出：-1
解释：该矩阵无法转变成全零矩阵
```

**提示：**

- `m == mat.length`
- `n == mat[0].length`
- `1 <= m <= 3`
- `1 <= n <= 3`
- `mat[i][j]` 是 0 或 1 。

## 分析与代码

分析：呃，有空再看，先放着二哥的代码。

代码：

```java
class Solution {
    // bit的每一位代表每个格子是否被选择，n*m的矩阵压成一个bit，这里的n在题目中表示n*m
    private int count(int bit, int n) {
        int ret = 0;
        for (int i = 0;i < n;i++) {
            if ((bit & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }
    // 方向数组，注意最后的0,0，因为自身也有可能被选择
    private final int[][] d = {{0,1},{1,0},{0,-1},{-1,0},{0,0}};
    // 判断选择串bit是否能满足题目要求
    boolean judge(int[][] mat, int bit) {
        for (int i = 0;i < mat.length;i++) {
            for (int j = 0;j < mat[0].length;j++) {
                int sum = mat[i][j];
                for (int k = 0;k < d.length;k++) {
                    int dx = i + d[k][0];
                    int dy = j + d[k][1];
                    
                    if (dx >= 0 && dx < mat.length && dy >= 0 && dy < mat[0].length) {
                        int t = dx * mat[0].length + dy;
                        if ((bit & (1 << t)) != 0) {
                            sum++;
                        }
                    }
                }
                // 如果sum是奇数，说明经过bit转换后，mat[i][j]变成了1
                if (sum % 2 != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public int minFlips(int[][] mat) {
        int n = mat.length * mat[0].length;
        int ret = 100;
        for (int i = 0;i < (1 << n);i++) {
            if (judge(mat, i)) {
                ret = Math.min(ret, count(i, n));
            }
        }
        if (ret == 100) {
            return -1;
        }
        return ret;
    }
}

作者：小白二号
链接：https://leetcode-cn.com/circle/discuss/FpOkuW/view/Oa59qT/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```



# 小结

可以用 subList() 来获得子集合，学到了。