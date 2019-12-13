class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dump = new ListNode(-1), pre = dump, cur = head;
        dump.next = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                cur = cur.next;
                continue;
            }
            pre = cur;
            cur = cur.next;
        }
        return dump.next;
    }
}