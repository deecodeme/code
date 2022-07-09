package com.poc.code.LinkedList;

import java.util.ArrayList;

public class Reverse {

    private static Node reverse(Node head) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        // 1 <- 2 <- 3 <- 4 <- 5 <- 6
        Node current = head;
        Node temp = null;
        Node next = current.getNext();
        while (current != null && next != null) {
            next = current.getNext();
            current.setNext(temp);
            temp = current;
            if(next != null){
                current = next;
            }
        }
        return current;
    }

    private static Node reverseLinkedListInPairRecursive(Node head){
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        // 2 -> 1 -> 4 -> 3 -> 6 -> 5
        if(head != null && head.getNext() != null){
            Node nextToNext = head.getNext().getNext();

            Node next = head.getNext();
            next.setNext(head);
            head.setNext(reverseLinkedListInPairRecursive(nextToNext));
            return next;
        }
        return head;
    }

    private static Node reverseLinkedListInPairIterative(Node head){

        return null;
    }

    private static LinkedList CreateList() {
        Node head = Node.builder().data(1).next(null).build();
        LinkedList list = LinkedList.builder().head(head).build();
        list.InsertAtEnd(new Node(2, null));
        list.InsertAtEnd(new Node(3, null));
        list.InsertAtEnd(new Node(4, null));
        list.InsertAtEnd(new Node(5, null));
        list.InsertAtEnd(new Node(6, null));
        list.InsertAtEnd(new Node(7, null));
        return list;
    }

    private static String printList(Node node) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (node != null){
            arrayList.add(node.getData());
            node = node.getNext();
        }
        return arrayList.toString();
    }

    public static void main(String[] args) {
        LinkedList list = CreateList();

        System.out.println("Linked list: "+printList(list.getHead()));
        Node reversedNode = reverse(list.getHead());
        System.out.println("Revered Linked list: "+printList(reversedNode));

        LinkedList list1 = CreateList();
        System.out.println("Linked list: " +printList(list1.getHead()));
        Node alternateReversedNode = reverseLinkedListInPairRecursive(list1.getHead());
        System.out.println("Alternate Revered Linked list: "+printList(alternateReversedNode));
    }
}
