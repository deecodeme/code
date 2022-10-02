package com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.framework;

public interface Driver {
    boolean isSupportedType(String DB);

    Connection getConnection();
}
