package com.poc.code.practices.effectiveJava.objectReuse;

import java.util.regex.Pattern;

public enum ObjectReuseExample {
    INSTANCE;

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    public String doNotUseStringConstructor() {
        return new String("CREATES A NEW OBJECT EVERY TIME");
    }

    public String useImmutableSharedString() {
        return "STRING INSTANCE FROM JVM HEAP";
    }

    @Deprecated
    public Boolean doNotUseBooleanConstructor() {
        return new Boolean(true);
    }

    public Boolean useImmutableSharedBoolean() {
        return Boolean.valueOf(true);
    }

    public boolean doNotUseStringMatcher(String s, String regex) {
        return s.matches(regex);
    }

    public boolean cacheCompiledRegexPattern(String s) {
        return ROMAN.matcher(s).matches();
    }

}
