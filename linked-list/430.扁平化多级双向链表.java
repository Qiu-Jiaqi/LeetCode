class Solution {
    private Node prev = null;

    public Node flatten(Node head) {
        helper(head);
        return head;
    }

    public void helper(Node head) {
        if (head == null) {
            return;
        }
        Node next = head.next;
        if (prev != null) {
            prev.next = head;
            head.prev = prev;
        }
        prev = head;
        helper(head.child);
        head.child = null;
        helper(next);
    }
}