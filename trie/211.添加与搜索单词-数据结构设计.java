class TrieNode {
    public static final int N = 26;
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

    public boolean search(TrieNode cur, String word, int index) {
        if (cur == null) {
            return false;
        }
        if (index == word.length()) {
            return cur.isWord;
        }
        if (word.charAt(index) == '.') {
            boolean res = false;
            for (TrieNode child : cur.children) {
                res |= search(child, word, index + 1);
            }
            return res;
        }
        cur = cur.children[word.charAt(index) - 'a'];
        return search(cur, word, index + 1);
    }
}

class WordDictionary {
    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        root.insert(word);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot
     * character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return root.search(root, word, 0);
    }
}