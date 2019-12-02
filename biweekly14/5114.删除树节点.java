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