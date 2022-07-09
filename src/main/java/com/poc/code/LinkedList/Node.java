package com.poc.code.LinkedList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Node {
    private int data;
    private Node next;
}
