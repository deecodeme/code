package com.poc.code.practices.effectiveJava.DI;

/*
A static factory to get Lexicons
 */
public interface LexiconFactory {
    default Lexicon get() {
        return EnglishLexicon.INSTANCE;
    }
}
