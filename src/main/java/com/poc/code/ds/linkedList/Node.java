package com.poc.code.ds.linkedList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Node<T> {
    private T data;
    private Node<T> next;
}
