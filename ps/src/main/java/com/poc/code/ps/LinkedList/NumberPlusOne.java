package com.poc.code.ps.LinkedList;

import java.util.LinkedList;

public class NumberPlusOne {
    public ListNode plusOne(ListNode head) {
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        while (!stack.isEmpty() && stack.peek().val == 9) {
            ListNode n = stack.pop();
            n.val = 0;
        }
        if (stack.isEmpty()) {
            return new ListNode(1, head);
        } else {
            ListNode n = stack.pop();
            n.val++;
            return head;
        }
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
