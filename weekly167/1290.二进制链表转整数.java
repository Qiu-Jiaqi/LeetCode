/* *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        String s = "";
        while (head != null) {
            s += head.val;
            head = head.next;
        }
        return Integer.parseInt(s, 2);
    }
}