package com.poc.code.practices.effectiveJava.objectReuse;

import com.poc.code.practices.effectiveJava.objectReuse.ObjectReuseExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ObjectReuseExampleTest {
    private Logger logger = LoggerFactory.getLogger(ObjectReuseExample.class.getName());

    @Test
    void doNotUseStringConstructor() {
        Assertions.assertFalse(ObjectReuseExample.INSTANCE.doNotUseStringConstructor()
                == ObjectReuseExample.INSTANCE.doNotUseStringConstructor());
    }

    @Test
    void useImmutableSharedString() {
        Assertions.assertTrue(ObjectReuseExample.INSTANCE.useImmutableSharedString()
                == ObjectReuseExample.INSTANCE.useImmutableSharedString());
    }

    @Test
    void doNotUseBooleanConstructor() {
        Assertions.assertFalse(ObjectReuseExample.INSTANCE.doNotUseBooleanConstructor()
                == ObjectReuseExample.INSTANCE.doNotUseBooleanConstructor());
    }

    @Test
    void useImmutableSharedBoolean() {
        Assertions.assertTrue(ObjectReuseExample.INSTANCE.useImmutableSharedBoolean()
                == ObjectReuseExample.INSTANCE.useImmutableSharedBoolean());
    }

    @Test
    void cacheCompiledRegexPattern() {
        Assertions.assertTrue(ObjectReuseExample.INSTANCE.cacheCompiledRegexPattern("VI"));
        Assertions.assertFalse(ObjectReuseExample.INSTANCE.cacheCompiledRegexPattern("6"));
    }

    @Test
    void doNotUseStringMatcher() {
        Assertions.assertTrue(ObjectReuseExample.INSTANCE.doNotUseStringMatcher("VI",
                "^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"));
        Assertions.assertFalse(ObjectReuseExample.INSTANCE.doNotUseStringMatcher("6",
                "^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"));
    }

    @Test
    void regexCompileTimeDifference() {
        long cachedRegexStart = System.nanoTime();
        ObjectReuseExample.INSTANCE.cacheCompiledRegexPattern("VI");
        long cachedRegexTotalTime = System.nanoTime() - cachedRegexStart;
        long regexStringMatcherStart = System.nanoTime();
        ObjectReuseExample.INSTANCE.doNotUseStringMatcher("VI",
                "^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
        long regexStringMatcherTotalTime = System.nanoTime() - regexStringMatcherStart;
        logger.info("cachedRegexTotalTime: {}", cachedRegexTotalTime);
        logger.info("regexStringMatcherStart: {}", regexStringMatcherStart);
        logger.info("regexStringMatcherTotalTime - cachedRegexTotalTime: {}", regexStringMatcherTotalTime - cachedRegexTotalTime);
    }
}