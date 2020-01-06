class Solution {
    public String freqAlphabets(String s) {
        String res = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '#') {
                res = (char) (s.charAt(--i) - '0' - 1 + 'a' + (s.charAt(--i) - '0') * 10) + res;
            } else {
                res = (char) (s.charAt(i) - '0' - 1 + 'a') + res;
            }
        }
        return res;
    }
}