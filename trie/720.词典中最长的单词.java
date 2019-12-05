class TrieNode {
    private static final int N = 26;
    public boolean isWord;
    public TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[N];
    }

    public void insert(String word) {
        TrieNode cur = this;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        TrieNode cur = this;
        for (char ch : word.toCharArray()) {
            cur = cur.children[ch - 'a'];
            if (cur == null || !cur.isWord) {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    TrieNode root = new TrieNode();

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        for (String word : words) {
            root.insert(word);
        }
        for (String word : words) {
            if (root.search(word)) {
                return word;
            }
        }
        return "";
    }
}