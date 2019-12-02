class Solution {
    public String toHexspeak(String num) {
        String res = new BigInteger(num).toString(16).toUpperCase();
        res = res.replace("1", "I");
        res = res.replace("0", "O");
        for (char ch : res.toCharArray()) {
            if (Character.isDigit(ch)) {
                return "ERROR";
            }
        }
        return res;
    }
}