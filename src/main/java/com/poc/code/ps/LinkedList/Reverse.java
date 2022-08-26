package com.poc.code.ps.LinkedList;

import com.poc.code.ds.linkedList.LinkedList;
import com.poc.code.ds.linkedList.Node;

import java.util.ArrayList;

public class Reverse {

    private static Node<Integer> reverse(Node<Integer> head) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        // 1 <- 2 <- 3 <- 4 <- 5 <- 6
        Node<Integer> current = head;
        Node<Integer> temp = null;
        Node<Integer> next = current.getNext();
        while (current != null && next != null) {
            next = current.getNext();
            current.setNext(temp);
            temp = current;
            if (next != null) {
                current = next;
            }
        }
        return current;
    }

    private static Node<Integer> reverseLinkedListInPairRecursive(Node<Integer> head) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        // 2 -> 1 -> 4 -> 3 -> 6 -> 5
        if (head != null && head.getNext() != null) {
            Node<Integer> nextToNext = head.getNext().getNext();
            Node<Integer> next = head.getNext();
            next.setNext(head);
            head.setNext(reverseLinkedListInPairRecursive(nextToNext));
            return next;
        }
        return head;
    }

    private static Node reverseLinkedListInPairIterative(Node head) {

        return null;
    }

    private static LinkedList CreateList() {
        Node<Integer> head = Node.<Integer>builder().data(1).next(null).build();
        LinkedList<Integer> list = LinkedList.<Integer>builder().head(head).build();
        list.InsertAtEnd(new Node<Integer>(2, null));
        list.InsertAtEnd(new Node<Integer>(3, null));
        list.InsertAtEnd(new Node<Integer>(4, null));
        list.InsertAtEnd(new Node<Integer>(5, null));
        list.InsertAtEnd(new Node<Integer>(6, null));
        list.InsertAtEnd(new Node<Integer>(7, null));
        return list;
    }

    private static String printList(Node<Integer> node) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (node != null) {
            arrayList.add(node.getData());
            node = node.getNext();
        }
        return arrayList.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = CreateList();

        System.out.println("Linked list: " + printList(list.getHead()));
        Node<Integer> reversedNode = reverse(list.getHead());
        System.out.println("Revered Linked list: " + printList(reversedNode));

        LinkedList<Integer> list1 = CreateList();
        System.out.println("Linked list: " + printList(list1.getHead()));
        Node<Integer> alternateReversedNode = reverseLinkedListInPairRecursive(list1.getHead());
        System.out.println("Alternate Revered Linked list: " + printList(alternateReversedNode));
    }
}
