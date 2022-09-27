package com.poc.code.effectiveJava.serviceProviderFramework.jdbc.framework;

public interface Driver {
    boolean isSupportedType(String DB);

    Connection getConnection();
}
