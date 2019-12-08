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