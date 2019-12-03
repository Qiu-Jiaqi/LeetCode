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

    public String search(String word) {
        TrieNode cur = this;
        String res = "";
        for (char ch : word.toCharArray()) {
            res += ch;
            if (cur.children[ch - 'a'] == null) {
                return null;
            }
            cur = cur.children[ch - 'a'];
            if (cur.isWord) {
                return res;
            }
        }
        return null;
    }
}

class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            root.insert(word);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String replace = root.search(words[i]);
            words[i] = replace == null ? words[i] : replace;
        }
        return String.join(" ", words);
    }
}