package com.poc.code.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private Node root;

    public Trie() {
        root = new Node('0', false);
    }

    public void insertWord(String word) {
        Node node = this.root;
        for (int i = 0; i < word.length(); i++) {
            if (node.getChildren().containsKey(word.charAt(i))) {
                node = node.getChildren().get(word.charAt(i));
            } else {
                Node newNode = new Node(word.charAt(i), false);
                node.getChildren().put(word.charAt(i), newNode);
                node = newNode;
            }
        }
        node.setAWord(true);
    }

    public List<String> getAutoSuggestion(String startWith) {
        Node node = getPrefixNode(startWith);

        //for every child node, find the words
        List<String> suggestions = new ArrayList<>();
        for (Node child : node.getChildren().values()) {
            findAllWordsStartingAt(child, suggestions, startWith);
        }
//        findAllWordsStartingAt(child, suggestions, startWith);
        return suggestions;
    }

    public void findAllWordsStartingAt(Node node, List<String> suggestions, String currentWord) {
        if (node == null) {
            return;
        }
        currentWord += node.getVal();
        if (node.isAWord()) {
            suggestions.add(currentWord);
        }
        for (Node child : node.getChildren().values()) {
            findAllWordsStartingAt(child, suggestions, currentWord);
        }
    }

    public Node getPrefixNode(String word) {
        Node node = this.root;
        for (int i = 0; i < word.length(); i++) {
            if (node.getChildren().containsKey(word.charAt(i))) {
                node = node.getChildren().get(word.charAt(i));
            } else {
                return null;
            }
        }
        return node;
    }
}
