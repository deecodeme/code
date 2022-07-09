package com.poc.code.autocomplete;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Character val;
    private Map<Character, Node> children;
    private boolean isAWord;

    public Node(Character val, boolean isAWord) {
        this.val = val;
        this.children = new HashMap<>();
        this.isAWord = isAWord;
    }

    public Character getVal() {
        return val;
    }

    public void setVal(Character val) {
        this.val = val;
    }

    public Map<Character, Node> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, Node> children) {
        this.children = children;
    }

    public boolean isAWord() {
        return isAWord;
    }

    public void setAWord(boolean AWord) {
        isAWord = AWord;
    }
}
