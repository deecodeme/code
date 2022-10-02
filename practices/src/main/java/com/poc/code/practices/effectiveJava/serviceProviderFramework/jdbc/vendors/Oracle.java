package com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.vendors;

import com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.framework.Connection;

class Oracle implements Connection {
    @Override
    public String execute() {
        return "executed on Oracle";
    }
}
