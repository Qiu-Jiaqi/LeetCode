class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int x = (tomatoSlices - 2 * cheeseSlices) / 2, y = cheeseSlices - x;
        if (4 * x + 2 * y != tomatoSlices || x < 0 || y < 0) {
            return new ArrayList<>();
        }
        return Arrays.asList(x, y);
    }
}