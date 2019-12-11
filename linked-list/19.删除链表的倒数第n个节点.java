class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = head, cur = head;
        for (int i = 0; i < n; i++) {
            prev = prev.next;
        }
        if (prev == null) {
            return head.next;
        }
        while (prev.next != null) {
            prev = prev.next;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }
}