package com.poc.code.practices.effectiveJava.builder.DI;

import com.poc.code.practices.effectiveJava.DI.EnglishLexicon;
import com.poc.code.practices.effectiveJava.DI.Lexicon;
import com.poc.code.practices.effectiveJava.DI.SpellChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpellCheckerTest {
    @Test
    public void construction() {
        Lexicon lexicon = null;
        Assertions.assertThrows(NullPointerException.class, () -> SpellChecker.getInstance(lexicon));
    }

    @Test
    public void constructionFactory() {
        SpellChecker spellChecker = SpellChecker.getInstance(() -> EnglishLexicon.INSTANCE);
        Assertions.assertTrue(spellChecker.isValid("mango"));
        Assertions.assertFalse(spellChecker.isValid("avocado"));
    }
}