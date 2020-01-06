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