package com.poc.code.ds.linkedList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkedList<T> {
    private Node<T> head;

    public void InsertAtBeginning(Node<T> node) {
        if (node != null) {
            node.setNext(head);
            head = node;
        }

    }

    public void InsertAtEnd(Node<T> node) {
        Node<T> current = head;
        while (current != null && current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(node);
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        Node current = head;
        while (current != null) {
            str.append(current.getData());
            str.append(" -> ");
            current = current.getNext();
        }
        return str.toString();
    }
}
