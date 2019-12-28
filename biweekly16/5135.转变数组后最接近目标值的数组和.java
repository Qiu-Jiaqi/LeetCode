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