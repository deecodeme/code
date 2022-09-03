package com.poc.code.ps.autocomplete;

public class TrieDemo {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insertWord("deepak");
        trie.insertWord("deelak");
        trie.insertWord("deak");

        System.out.println(trie.getAutoSuggestion("dee"));

    }
}
