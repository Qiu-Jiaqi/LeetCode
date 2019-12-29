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