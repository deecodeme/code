package com.poc.code.effectiveJava.serviceProviderFramework.jdbc.vendors;

import com.poc.code.effectiveJava.serviceProviderFramework.jdbc.framework.Connection;

class MySQL implements Connection {
    @Override
    public String execute() {
        return "executed on MySQL";
    }
}
