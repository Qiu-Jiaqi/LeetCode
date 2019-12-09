public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }
        while (intersect != head) {
            head = head.next;
            intersect = intersect.next;
        }
        return intersect;
    }

    public ListNode getIntersect(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
}