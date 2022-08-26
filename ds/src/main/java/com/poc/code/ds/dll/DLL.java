package com.poc.code.ds.dll;

import lombok.Data;

import java.util.Objects;
import java.util.Optional;

@Data
public class DLL<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public DLL() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public Node insertFirst(T data) {
        Node<T> node = new Node<T>(data);
        if (this.first != null) {
            node.setNext(this.first);
            this.first.setPrev(node);
            this.first = node;
        } else {
            this.first = this.last = node;
        }
        this.size++;
        return node;
    }

    public Node<T> insertLast(T data) {
        Node<T> node = new Node<>(data);
        if (this.last != null) {
            last.setNext(node);
            node.setPrev(this.last);
            this.last = node;
        } else {
            this.first = this.last = node;
        }
        this.size++;
        return node;
    }

    public Optional<Node<T>> getFirst() {
        if (this.first != null) {
            return Optional.of(this.first);
        }
        return Optional.empty();
    }

    public Optional<Node<T>> getLast() {
        if (this.last != null) {
            return Optional.of(this.last);
        }
        return Optional.empty();
    }

    public void moveToFrontData(Node<T> node) {
        Objects.requireNonNull(node);
        Objects.requireNonNull(this.first);
        T data = this.first.getData();
        this.first.setData(node.getData());
        node.setData(data);
    }

    public void moveToFront(Node<T> node) {
        Objects.requireNonNull(node);
        Objects.requireNonNull(this.first);
        if (node == this.first)
            return;
        Node<T> n = this.first.getNext();
        this.first.setPrev(node.getPrev());
        this.first.setNext(node.getNext());
        if (node == this.last) {
            this.last = this.first;
        }
        node.setPrev(null);
        node.setNext(n);
        this.first = node;
    }

    public void delete(Node<T> node) {
        Objects.requireNonNull(node);
        if (node == this.first) {
            deleteFirst();
        } else if (node == this.last) {
            deleteLast();
        } else {
            deleteInMiddle(node);
        }
    }

    public void deleteInMiddle(Node<T> node) {
        Objects.requireNonNull(node);
        Node<T> prev = node.getPrev();
        Node<T> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        this.size--;
    }

    public void deleteLast() {
        Objects.requireNonNull(this.last);
        if (this.first == this.last) {
            this.first = this.last = null;
        } else {
            this.last = this.last.getPrev();
            if (this.last != null) {
                this.last.setNext(null);
            }
        }
        this.size--;
    }

    public void deleteFirst() {
        Objects.requireNonNull(this.first);
        if (this.first == this.last) {
            this.first = this.last = null;
        } else {
            this.first = this.first.getNext();
            if (this.first != null) {
                this.first.setPrev(null);
            }
        }
        this.size--;
    }
}
