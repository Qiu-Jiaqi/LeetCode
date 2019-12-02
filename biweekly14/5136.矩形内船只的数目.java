class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        int rightUp_x = topRight[0], rightUp_y = topRight[1];
        int leftDown_x = bottomLeft[0], leftDown_y = bottomLeft[1];
        if (rightUp_x < leftDown_x || rightUp_y < leftDown_y || !sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (rightUp_x == leftDown_x && rightUp_y == leftDown_y) {
            return 1;
        }
        int mid_x = (rightUp_x + leftDown_x) >> 1, mid_y = (rightUp_y + leftDown_y) >>> 1;
        return countShips(sea, new int[] { mid_x, mid_y }, bottomLeft)
                + countShips(sea, topRight, new int[] { mid_x + 1, mid_y + 1 })
                + countShips(sea, new int[] { mid_x, rightUp_y }, new int[] { leftDown_x, mid_y + 1 })
                + countShips(sea, new int[] { rightUp_x, mid_y }, new int[] { mid_x + 1, leftDown_y });
    }
}