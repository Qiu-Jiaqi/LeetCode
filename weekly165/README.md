早上的周赛又是只做了两题，今天一天都在看题解，还好基本都弄懂了。

话说，都已经快考试了啊，我还没复习，还刷题，糟糕。不过刷题能麻木我，减轻一丝痛苦，复习却不能。

明天开始晚上还是复习好了。

# 5275. 找出井字棋的获胜者

传送门：[5275. 找出井字棋的获胜者](https://leetcode-cn.com/contest/weekly-contest-165/problems/find-winner-on-a-tic-tac-toe-game/)

## 题目描述

*A* 和 *B* 在一个 *3* x *3* 的网格上玩井字棋。

井字棋游戏的规则如下：

- 玩家轮流将棋子放在空方格 (" ") 上。
- 第一个玩家 A 总是用 "X" 作为棋子，而第二个玩家 B 总是用 "O" 作为棋子。
- "X" 和 "O" 只能放在空方格中，而不能放在已经被占用的方格上。
- 只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。
- 如果所有方块都放满棋子（不为空），游戏也会结束。
- 游戏结束后，棋子无法再进行任何移动。

给你一个数组 `moves`，其中每个元素是大小为 `2` 的另一个数组（元素分别对应网格的行和列），它按照 *A* 和 *B* 的行动顺序（先 *A* 后 *B*）记录了两人各自的棋子位置。

如果游戏存在获胜者（*A* 或 *B*），就返回该游戏的获胜者；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。

你可以假设 `moves` 都 **有效**（遵循井字棋规则），网格最初是空的，*A* 将先行动。

<!-- more -->

**示例 1：**

```
输入：moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
输出："A"
解释："A" 获胜，他总是先走。
"X  "    "X  "    "X  "    "X  "    "X  "
"   " -> "   " -> " X " -> " X " -> " X "
"   "    "O  "    "O  "    "OO "    "OOX"
```

**示例 2：**

```
输入：moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
输出："B"
解释："B" 获胜。
"X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
"   " -> " O " -> " O " -> " O " -> "XO " -> "XO " 
"   "    "   "    "   "    "   "    "   "    "O  "
```

**示例 3：**

```
输入：moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
输出："Draw"
输出：由于没有办法再行动，游戏以平局结束。
"XXO"
"OOX"
"XOX"
```

**示例 4：**

```
输入：moves = [[0,0],[1,1]]
输出："Pending"
解释：游戏还没有结束。
"X  "
" O "
"   "
```

**提示：**

- `1 <= moves.length <= 9`
- `moves[i].length == 2`
- `0 <= moves[i][j] <= 2`
- `moves` 里没有重复的元素。
- `moves` 遵循井字棋的规则。

## 分析与代码

分析：神烦的第一题。模拟，每走一步判断一次，只判断该点处的行列对角线，用 1 表示 A， -1 表示 B，方便判断。

代码：

```java
class Solution {
    public String tictactoe(int[][] moves) {
        int[][] board = new int[3][3];
        for (int i = 0; i < moves.length; i++) {
            int row = moves[i][0], col = moves[i][1];
            if (i % 2 == 0) {
                board[row][col] = 1;
                if (judge(board, row, col)) {
                    return "A";
                }
            } else {
                board[row][col] = -1;
                if (judge(board, row, col)) {
                    return "B";
                }
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    public boolean judge(int[][] board, int row, int col) {
        if (Math.abs(board[row][0] + board[row][1] + board[row][2]) == 3) {
            return true;
        }
        if (Math.abs(board[0][col] + board[1][col] + board[2][col]) == 3) {
            return true;
        }
        if (row - col == 0) {
            if (Math.abs(board[0][0] + board[1][1] + board[2][2]) == 3) {
                return true;
            }
        }
        if (row + col == 2) {
            if (Math.abs(board[0][2] + board[1][1] + board[2][0]) == 3) {
                return true;
            }
        }
        return false;
    }
}
```



# 5276. 不浪费原料的汉堡制作方案

传送门：[5276. 不浪费原料的汉堡制作方案](https://leetcode-cn.com/contest/weekly-contest-165/problems/number-of-burgers-with-no-waste-of-ingredients/)

## 题目描述

圣诞活动预热开始啦，汉堡店推出了全新的汉堡套餐。为了避免浪费原料，请你帮他们制定合适的制作计划。

给你两个整数 `tomatoSlices` 和 `cheeseSlices`，分别表示番茄片和奶酪片的数目。不同汉堡的原料搭配如下：

- **巨无霸汉堡：**4 片番茄和 1 片奶酪
- **小皇堡：**2 片番茄和 1 片奶酪

请你以 `[total_jumbo, total_small]`（[巨无霸汉堡总数，小皇堡总数]）的格式返回恰当的制作方案，使得剩下的番茄片 `tomatoSlices` 和奶酪片 `cheeseSlices` 的数量都是 `0`。

如果无法使剩下的番茄片 `tomatoSlices` 和奶酪片 `cheeseSlices` 的数量为 `0`，就请返回 `[]`。

**示例 1：**

```
输入：tomatoSlices = 16, cheeseSlices = 7
输出：[1,6]
解释：制作 1 个巨无霸汉堡和 6 个小皇堡需要 4*1 + 2*6 = 16 片番茄和 1 + 6 = 7 片奶酪。不会剩下原料。
```

**示例 2：**

```
输入：tomatoSlices = 17, cheeseSlices = 4
输出：[]
解释：只制作小皇堡和巨无霸汉堡无法用光全部原料。
```

**示例 3：**

```
输入：tomatoSlices = 4, cheeseSlices = 17
输出：[]
解释：制作 1 个巨无霸汉堡会剩下 16 片奶酪，制作 2 个小皇堡会剩下 15 片奶酪。
```

**示例 4：**

```
输入：tomatoSlices = 0, cheeseSlices = 0
输出：[0,0]
```

**示例 5：**

```
输入：tomatoSlices = 2, cheeseSlices = 1
输出：[0,1]
```

**提示：**

- `0 <= tomatoSlices <= 10^7`
- `0 <= cheeseSlices <= 10^7`

## 分析与代码

分析：比第一题还简单。二元一次方程，呃，小学数学，最后判断非负数，还有就是避免 x 除 2 计算出来的结果为小数，代回方程一验算。

代码：

```java
class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int x = (tomatoSlices - 2 * cheeseSlices) / 2, y = cheeseSlices - x;
        if (4 * x + 2 * y != tomatoSlices || x < 0 || y < 0) {
            return new ArrayList<>();
        }
        return Arrays.asList(x, y);
    }
}
```



# 5277. 统计全为 1 的正方形子矩阵

传送门：[5277. 统计全为 1 的正方形子矩阵](https://leetcode-cn.com/contest/weekly-contest-165/problems/count-square-submatrices-with-all-ones/)

## 题目描述

给你一个 `m * n` 的矩阵，矩阵中的元素不是 `0` 就是 `1`，请你统计并返回其中完全由 `1` 组成的 **正方形** 子矩阵的个数。

**示例 1：**

```
输入：matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
输出：15
解释： 
边长为 1 的正方形有 10 个。
边长为 2 的正方形有 4 个。
边长为 3 的正方形有 1 个。
正方形的总数 = 10 + 4 + 1 = 15.
```

**示例 2：**

```
输入：matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
输出：7
解释：
边长为 1 的正方形有 6 个。 
边长为 2 的正方形有 1 个。
正方形的总数 = 6 + 1 = 7.
```

**提示：**

- `1 <= arr.length <= 300`
- `1 <= arr[0].length <= 300`
- `0 <= arr[i][j] <= 1`

## 分析与代码

分析：看二哥题解看明白了，这样的题解我是想不出了，时间复杂度$O(mn)$，起码看懂了。

动态规划，$dp[i][j]$表示以$matrix[i][j]$为右下角的正方形个数。

遍历，若为 0 直接下一个；若为 1，先更新$dp[i][j] = 1$，若为第一行和第一列，res 直接加 1 进入下一个；否则，取左方和上方的 dp 较小值 d，若为 0，说明至少有一个为 0，不可能构成更大的正方形，res 加 1 进入下一个；若不为 0，只要判断$matrix[i - d][j - d] == 1$，若为 1，说明能构成一个以 d + 1 为边长的正方形，则正方形个数应为 d + 1，否则构成以 d 为边长的正方形。画图比较清晰。

代码：

```java
class Solution {
    public int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                dp[i][j] = 1;
                if (i == 0 || j == 0) {
                    res++;
                    continue;
                }
                int d = Math.min(dp[i - 1][j], dp[i][j - 1]);
                if (d == 0) {
                    res++;
                    continue;
                }
                dp[i][j] = matrix[i - d][j - d] == 1 ? (d + 1) : d;
                res += dp[i][j];
            }
        }
        return res;
    }
}
```



# 5278. 分割回文串 III

传送门：[5278. 分割回文串 III](https://leetcode-cn.com/contest/weekly-contest-165/problems/palindrome-partitioning-iii/)

## 题目描述

给你一个由小写字母组成的字符串 `s`，和一个整数 `k`。

请你按下面的要求分割字符串：

- 首先，你可以将 `s` 中的部分字符修改为其他的小写英文字母。
- 接着，你需要把 `s` 分割成 `k` 个非空且不相交的子串，并且每个子串都是回文串。

请返回以这种方式分割字符串所需修改的最少字符数。

**示例 1：**

```
输入：s = "abc", k = 2
输出：1
解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。
```

**示例 2：**

```
输入：s = "aabbc", k = 3
输出：0
解释：你可以把字符串分割成 "aa"、"bb" 和 "c"，它们都是回文串。
```

**示例 3：**

```
输入：s = "leetcode", k = 8
输出：0
```

**提示：**

- `1 <= k <= s.length <= 100`
- `s` 中只含有小写英文字母。

## 分析与代码

分析：这题也是看二哥题解看明白的，动态规划，$dp[i][j]$表示前 i 个字符，分割 j 次的最少次数。

函数`toPalindrome(String s, int left, int right)`求 left 到 right 下标转换为回文串所需的次数。

在第 p 个位置切割时，

转移方程$dp[i][j] = min(dp[i][j], dp[p][j-1] + toPalindrome(p, i))$

代码：

```java
class Solution {
    public int palindromePartition(String s, int k) {
        int[][] dp = new int[s.length() + 1][k + 1];
        for (int[] a : dp) {
            Arrays.fill(a, 101);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int p = 0; p < i; p++) {
                int cache = toPalindrome(s, p, i - 1);
                for (int j = 1; j <= k; j++) {
                    if (j > i) {
                        break;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + cache);
                }
            }
        }
        return dp[s.length()][k];
    }

    public int toPalindrome(String s, int left, int right) {
        int res = 0;
        while (left < right) {
            res += s.charAt(left++) == s.charAt(right--) ? 0 : 1;
        }
        return res;
    }
}
```



# 小结

动态规划还是不会做，做不出，但是看题解就能看懂。要找到表示的状态和状态转移。