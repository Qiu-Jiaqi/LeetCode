class TrieNode {
    private static final int N = 26;
    public boolean isWord;
    public TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[N];
    }
}

class Trie {
    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                return false;
            }
            cur = cur.children[ch - 'a'];
        }
        return cur.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char ch : prefix.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                return false;
            }
            cur = cur.children[ch - 'a'];
        }
        return true;
    }
}