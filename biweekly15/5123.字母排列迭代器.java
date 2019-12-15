class CombinationIterator {
    String s;
    List<String> cache = new ArrayList<>();
    int num = 0;

    public CombinationIterator(String characters, int combinationLength) {
        s = characters;
        backtrack("", combinationLength, 0);
    }

    public void backtrack(String cur, int combinationLength, int index) {
        if (cur.length() == combinationLength) {
            cache.add(cur);
            return;
        }
        for (int i = index; i < s.length(); i++) {
            backtrack(cur + s.charAt(i), combinationLength, i + 1);
        }
    }

    public String next() {
        return cache.get(num++);
    }

    public boolean hasNext() {
        return num < cache.size();
    }
}