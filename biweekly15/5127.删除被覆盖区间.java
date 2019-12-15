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