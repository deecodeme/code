package com.poc.code.ds.dll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DLLTest {

    @Test
    void insertFirst() {
        DLL<Integer> dll = new DLL<>();
        dll.insertFirst(1);
        Assertions.assertEquals(1, dll.getSize());
        Assertions.assertEquals(1, dll.getFirst().orElseThrow().getData());
        dll.insertFirst(-1);
        Assertions.assertEquals(2, dll.getSize());
        Assertions.assertEquals(-1, dll.getFirst().orElseThrow().getData());
    }

    @Test
    void insertLast() {
        DLL<Character> dll = new DLL<>();
        dll.insertLast('a');
        Assertions.assertEquals(1, dll.getSize());
        Assertions.assertEquals('a', dll.getLast().orElseThrow().getData());
        dll.insertLast('b');
        Assertions.assertEquals(2, dll.getSize());
        Assertions.assertEquals('b', dll.getLast().orElseThrow().getData());
    }

    @Test
    void getFirst() {
        DLL<Integer> dll = new DLL<>();
        Assertions.assertTrue(dll.getFirst().equals(Optional.empty()));
        dll.insertFirst(1);
        Assertions.assertEquals(1, dll.getFirst().orElseThrow().getData());
    }

    @Test
    void getLast() {
        DLL<Integer> dll = new DLL<>();
        Assertions.assertTrue(dll.getLast().equals(Optional.empty()));
        dll.insertLast(1);
        Assertions.assertEquals(1, dll.getLast().orElseThrow().getData());
    }

    @Test
    void delete() {
        DLL<Integer> dll = new DLL<>();
        Node<Integer> node1 = dll.insertFirst(1);
        Node<Integer> node2 = dll.insertLast(2);
        Node<Integer> node3 = dll.insertLast(3);
        Node<Integer> node4 = dll.insertLast(4);
        Node<Integer> node5 = dll.insertLast(5);
        Assertions.assertEquals(1, dll.getFirst().orElseThrow().getData());
        Assertions.assertEquals(5, dll.getSize());
        dll.delete(node1);
        Assertions.assertEquals(4, dll.getSize());
        Assertions.assertEquals(2, dll.getFirst().orElseThrow().getData());
        dll.delete(node5);
        Assertions.assertEquals(3, dll.getSize());
        Assertions.assertEquals(4, dll.getLast().orElseThrow().getData());
        dll.delete(node3);
        Assertions.assertEquals(2, dll.getSize());
        Assertions.assertEquals(2, dll.getFirst().orElseThrow().getData());
        Assertions.assertEquals(4, dll.getLast().orElseThrow().getData());
        dll.delete(node2);
        Assertions.assertEquals(1, dll.getSize());
        Assertions.assertEquals(4, dll.getFirst().orElseThrow().getData());
        Assertions.assertEquals(4, dll.getLast().orElseThrow().getData());
        dll.delete(node4);
        Assertions.assertEquals(0, dll.getSize());
        Assertions.assertTrue(Optional.empty().equals(dll.getFirst()));
        Assertions.assertTrue(Optional.empty().equals(dll.getLast()));
    }


    @Test
    void moveToFront() {
        DLL<Integer> dll = new DLL<>();
        Node<Integer> node1 = dll.insertFirst(1);
        Node<Integer> node2 = dll.insertLast(2);
        Node<Integer> node3 = dll.insertLast(3);
        Node<Integer> node4 = dll.insertLast(4);
        dll.moveToFront(node2);
        Assertions.assertEquals(4, dll.getSize());
        Assertions.assertEquals(node2, dll.getFirst().get());
        Assertions.assertEquals(4, dll.getSize());
        dll.moveToFront(node3);
        Assertions.assertEquals(4, dll.getSize());
        Assertions.assertEquals(node3, dll.getFirst().get());
        Assertions.assertEquals(4, dll.getSize());
        dll.moveToFront(node4);
        Assertions.assertEquals(node4, dll.getFirst().get());
        Assertions.assertEquals(node3, dll.getLast().orElseThrow());
    }
}