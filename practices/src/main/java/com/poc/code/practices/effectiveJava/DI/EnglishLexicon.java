package com.poc.code.practices.effectiveJava.DI;

import java.util.Set;
import java.util.TreeSet;

public enum EnglishLexicon implements Lexicon {
    INSTANCE;
    private final Set<String> dictionary;

    EnglishLexicon() {
        dictionary = new TreeSet<>();
        dictionary.add("apple");
        dictionary.add("mango");
        dictionary.add("banana");
    }

    @Override
    public boolean isValid(String word) {
        return dictionary.contains(word);
    }
}
