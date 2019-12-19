class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dumb = new ListNode(0), cur = dumb;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = v1 + v2 + carry;
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dumb.next;
    }
}