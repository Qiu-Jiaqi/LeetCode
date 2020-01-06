# 1309. 解码字母到整数映射

传送门：[1309. 解码字母到整数映射](https://leetcode-cn.com/problems/decrypt-string-from-alphabet-to-integer-mapping/)

## 题目描述

给你一个字符串 `s`，它由数字（`'0'` - `'9'`）和 `'#'` 组成。我们希望按下述规则将 `s` 映射为一些小写英文字符：

- 字符（`'a'` - `'i'`）分别用（`'1'` - `'9'`）表示。
- 字符（`'j'` - `'z'`）分别用（`'10#'` - `'26#'`）表示。 

返回映射之后形成的新字符串。

题目数据保证映射始终唯一。

**示例 1：**

```
输入：s = "10#11#12"
输出："jkab"
解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
```

**示例 2：**

```
输入：s = "1326#"
输出："acz"
```

**示例 3：**

```
输入：s = "25#"
输出："y"
```

**示例 4：**

```
输入：s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
输出："abcdefghijklmnopqrstuvwxyz"
```

**提示：**

- `1 <= s.length <= 1000`
- `s[i]` 只包含数字（`'0'`-`'9'`）和 `'#'` 字符。
- `s` 是映射始终存在的有效字符串。

## 分析与代码

分析：个位数没有字符 ‘#’，而 ’#‘ 是在结尾处，所以从右往左遍历。每次读到 ’#‘ 则说明前面必有两位数字，否则直接得出字符。

代码：

```java
class Solution {
    public String freqAlphabets(String s) {
        String res = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '#') {
                res = (char) (s.charAt(--i) - '0' - 1 + 'a' + (s.charAt(--i) - '0') * 10) + res;
            } else {
                res = (char) (s.charAt(i) - '0' - 1 + 'a') + res;
            }
        }
        return res;
    }
}
```



# 1310. 子数组异或查询

传送门：[1310. 子数组异或查询](https://leetcode-cn.com/problems/xor-queries-of-a-subarray/)

## 题目描述

有一个正整数数组 `arr`，现给你一个对应的查询数组 `queries`，其中 `queries[i] = [Li, Ri]`。

对于每个查询 `i`，请你计算从 `Li` 到 `Ri` 的 **XOR** 值（即 `arr[Li] xor arr[Li+1] xor ... xor arr[Ri]`）作为本次查询的结果。

并返回一个包含给定查询 `queries` 所有结果的数组。

**示例 1：**

```
输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
输出：[2,7,14,8] 
解释：
数组中元素的二进制表示形式是：
1 = 0001 
3 = 0011 
4 = 0100 
8 = 1000 
查询的 XOR 值为：
[0,1] = 1 xor 3 = 2 
[1,2] = 3 xor 4 = 7 
[0,3] = 1 xor 3 xor 4 xor 8 = 14 
[3,3] = 8
```

**示例 2：**

```
输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
输出：[8,0,4,4]
```

**提示：**

- `1 <= arr.length <= 3 * 10^4`
- `1 <= arr[i] <= 10^9`
- `1 <= queries.length <= 3 * 10^4`
- `queries[i].length == 2`
- `0 <= queries[i][0] <= queries[i][1] < arr.length`

## 分析与代码

分析：就是简单的前缀和，只是不再是求和，而是求异或。前缀和用减去掉前一段的和值，而异或则直接使用异或求去掉前一段的异或值，相同的数字异或为 0.

代码：

```java
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] dp = new int[arr.length + 1];
        dp[0] = 0;
        for (int i = 1; i <= arr.length; i++) {
            dp[i] = dp[i - 1] ^ arr[i - 1];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = dp[queries[i][1] + 1] ^ dp[queries[i][0]];
        }
        return res;
    }
}
```



# 1311. 获取你好友已观看的视频

传送门：[1311. 获取你好友已观看的视频](https://leetcode-cn.com/problems/get-watched-videos-by-your-friends/)

## 题目描述

有 `n` 个人，每个人都有一个 `0` 到 `n-1` 的唯一 *id* 。

给你数组 `watchedVideos` 和 `friends` ，其中 `watchedVideos[i]` 和 `friends[i]` 分别表示 `id = i` 的人观看过的视频列表和他的好友列表。

Level **1** 的视频包含所有你好友观看过的视频，level **2** 的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 **k** 的视频包含所有从你出发，最短距离为 **k** 的好友观看过的视频。

给定你的 `id` 和一个 `level` 值，请你找出所有指定 `level` 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按名字字典序从小到大排列。

**示例 1：**

```
输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
输出：["B","C"] 
解释：
你的 id 为 0 ，你的朋友包括：
id 为 1 -> watchedVideos = ["C"] 
id 为 2 -> watchedVideos = ["B","C"] 
你朋友观看过视频的频率为：
B -> 1 
C -> 2
```

**示例 2：**

```
输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
输出：["D"]
解释：
你的 id 为 0 ，你朋友的朋友只有一个人，他的 id 为 3 。
```

**提示：**

- `n == watchedVideos.length == friends.length`
- `2 <= n <= 100`
- `1 <= watchedVideos[i].length <= 100`
- `1 <= watchedVideos[i][j].length <= 8`
- `0 <= friends[i].length < n`
- `0 <= friends[i][j] < n`
- `0 <= id < n`
- `1 <= level < n`
- 如果 `friends[i]` 包含 `j` ，那么 `friends[j]` 包含 `i`

## 分析与代码

分析：广度优先遍历使用队列查找符合层数要求的朋友；哈希表记录已出现的朋友，防止多次访问；哈希映射记录视频及其观看频率；最后排序。

HashMap 的排序需要转换为 List，然后再排序。

`Collections.sort(list, Comparator.comparing((Map.Entry<String, Integer> o) -> o.getValue()).thenComparing((Map.Entry<String, Integer> o) -> o.getKey()));`

表示先按观看频率升序排序，然后按字典序排序。

代码：

```java
import java.util.Map.Entry;

class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] ht = new boolean[friends.length];
        q.offer(id);
        ht[id] = true;
        while (!q.isEmpty() && level-- > 0) {
            for (int i = q.size(); i > 0; i--) {
                int cur = q.poll();
                for (int j = 0; j < friends[cur].length; j++) {
                    if (!ht[friends[cur][j]]) {
                        q.offer(friends[cur][j]);
                        ht[friends[cur][j]] = true;
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        if (q.isEmpty()) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int num : q) {
            for (String key : watchedVideos.get(num)) {
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, Comparator.comparing((Map.Entry<String, Integer> o) -> o.getValue())
                .thenComparing((Map.Entry<String, Integer> o) -> o.getKey()));
        for (Entry<String, Integer> entry : list) {
            res.add(entry.getKey());
        }
        return res;
    }
}
```



# 1312. 让字符串成为回文串的最少插入次数

传送门：[1312. 让字符串成为回文串的最少插入次数](https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/)

## 题目描述

给你一个字符串 `s` ，每一次操作你都可以在字符串的任意位置插入任意字符。

请你返回让 `s` 成为回文串的 **最少操作次数** 。

「回文串」是正读和反读都相同的字符串。

**示例 1：**

```
输入：s = "zzazz"
输出：0
解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
```

**示例 2：**

```
输入：s = "mbadm"
输出：2
解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
```

**示例 3：**

```
输入：s = "leetcode"
输出：5
解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
```

**示例 4：**

```
输入：s = "g"
输出：0
```

**示例 5：**

```
输入：s = "no"
输出：1
```

**提示：**

- `1 <= s.length <= 500`
- `s` 中所有字符都是小写字母。

## 分析与代码

分析：简单的动态规划问题，$dp[i][j]$表示下标 i 到 j 之间最少插入次数。则有：

$dp[i][j] = dp[i+1][j-1];s[i] == s[j]$

$dp[i][j] = min(dp[i+1][j],dp[i][j-1]) + 1; s[i] != s[j]$

也可转换为求最长公共子序列问题，最少插入次数即为$s.length() - LCS(s, s.reverse())$

代码：

```java
class Solution {
    char[] sc;
    int[][] dp;

    public int backtrack(int start, int end) {
        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != -1) {
            return dp[start][end];
        }
        if (sc[start] == sc[end]) {
            dp[start][end] = backtrack(start + 1, end - 1);
        } else {
            dp[start + 1][end] = backtrack(start + 1, end);
            dp[start][end - 1] = backtrack(start, end - 1);
            dp[start][end] = Math.min(dp[start + 1][end], dp[start][end - 1]) + 1;
        }
        return dp[start][end];
    }

    public int minInsertions(String s) {
        // 1. 记忆化递归
        sc = s.toCharArray();
        dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return backtrack(0, s.length() - 1);

        // 2. 动态规划
        dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][s.length() - 1];

        // 3. 转为最长公共子序列问题
        String t = new StringBuilder(s).reverse().toString();
        return s.length() - LCS(s, t);
    }

    public int LCS(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}
```



# 小结

最少回文串的插入问题，可以直接动态规划，也可以转换为求最长公共子序列的问题。

