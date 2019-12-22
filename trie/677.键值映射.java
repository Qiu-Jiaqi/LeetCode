class TrieNode {
    public int val;
    public int sum;
    public Map<Character, TrieNode> children;

    public TrieNode() {
        children = new HashMap<>();
    }
}

class MapSum {
    private TrieNode root;
    private int oldVal;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public boolean isExist(String key) {
        TrieNode cur = root;
        for (char ch : key.toCharArray()) {
            if (!cur.children.containsKey(ch)) {
                return false;
            }
            cur = cur.children.get(ch);
        }
        oldVal = cur.val;
        return true;
    }

    public void insert(String key, int val) {
        boolean isExist = isExist(key);
        TrieNode cur = root;
        for (char ch : key.toCharArray()) {
            if (!cur.children.containsKey(ch)) {
                cur.children.put(ch, new TrieNode());
            }
            cur = cur.children.get(ch);
            if (isExist) {
                cur.sum -= oldVal;
            }
            cur.sum += val;
        }
        cur.val = val;
    }

    public int sum(String prefix) {
        TrieNode cur = root;
        for (char ch : prefix.toCharArray()) {
            if (!cur.children.containsKey(ch)) {
                return 0;
            }
            cur = cur.children.get(ch);
        }
        return cur.sum;
    }
}