package com.poc.code.effectiveJava.serviceProviderFramework.example.providers;

import com.poc.code.effectiveJava.serviceProviderFramework.example.framework.Provider;
import com.poc.code.effectiveJava.serviceProviderFramework.example.framework.Providers;

public class ProviderB implements Provider {
    private static final String TYPE = "ProviderB";

    public static final ProviderB INSTANCE = new ProviderB();

    private ProviderB() {
    }

    @Override
    public String type() {
        return TYPE;
    }

    public void register() {
        Providers.register(TYPE, INSTANCE);
    }
}
