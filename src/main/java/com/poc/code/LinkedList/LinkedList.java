package com.poc.code.LinkedList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkedList {
    private Node head;

    public void InsertAtBeginning(Node node){
        if(node != null){
            node.setNext(head);
            head = node;
        }

    }

    public void InsertAtEnd(Node node){
        Node current = head;
        while (current != null && current.getNext() != null){
            current = current.getNext();
        }

        current.setNext(node);
    }

    public String toString(){
        StringBuffer str = new StringBuffer();
        Node current = head;
        while (current != null){
            str.append(current.getData());
            str.append(" -> ");
            current = current.getNext();
        }
        return str.toString();
    }
}
