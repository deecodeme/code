package com.poc.code.effectiveJava.serviceProviderFramework.example.client;

import com.poc.code.effectiveJava.serviceProviderFramework.example.framework.Providers;
import com.poc.code.effectiveJava.serviceProviderFramework.example.framework.Provider;

public class ProviderBUser implements ProviderUser {
    private Provider provider;

    private ProviderBUser(Provider provider) {
        this.provider = provider;
    }

    public static ProviderBUser of() {
        return new ProviderBUser(Providers.of("ProviderB"));
    }

    @Override
    public String getProviderType() {
        return this.provider.type();
    }
}
