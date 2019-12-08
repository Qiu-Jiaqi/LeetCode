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