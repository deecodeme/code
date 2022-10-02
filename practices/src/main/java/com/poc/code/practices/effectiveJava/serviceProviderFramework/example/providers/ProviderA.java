package com.poc.code.practices.effectiveJava.serviceProviderFramework.example.providers;

import com.poc.code.practices.effectiveJava.serviceProviderFramework.example.framework.Provider;
import com.poc.code.practices.effectiveJava.serviceProviderFramework.example.framework.Providers;

public class ProviderA implements Provider {
    private static final String TYPE = "ProviderA";

    public static final ProviderA INSTANCE = new ProviderA();

    private ProviderA() {
    }

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public void register() {
        Providers.register(TYPE, INSTANCE);
    }
}
