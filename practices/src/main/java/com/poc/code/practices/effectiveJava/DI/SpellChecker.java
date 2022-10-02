package com.poc.code.practices.effectiveJava.DI;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public class SpellChecker {
    private Lexicon dictionary;

    private SpellChecker(@Nonnull Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    /*
    Constructor accepting factory method pattern
     */
    private SpellChecker(@Nonnull Supplier<? extends Lexicon> factory) {
        this.dictionary = factory.get();
    }

    public static SpellChecker getInstance(@Nonnull Lexicon dictionary) {
        return new SpellChecker(dictionary);
    }

    public static SpellChecker getInstance(@Nonnull Supplier<Lexicon> dictionaryFactory) {
        return new SpellChecker(dictionaryFactory);
    }

    public boolean isValid(String word) {
        return this.dictionary.isValid(word);
    }
}
