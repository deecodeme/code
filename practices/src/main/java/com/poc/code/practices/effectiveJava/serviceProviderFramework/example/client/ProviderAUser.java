package com.poc.code.practices.effectiveJava.serviceProviderFramework.example.client;

import com.poc.code.practices.effectiveJava.serviceProviderFramework.example.framework.Providers;
import com.poc.code.practices.effectiveJava.serviceProviderFramework.example.framework.Provider;

public class ProviderAUser implements ProviderUser{
    private Provider provider;

    private ProviderAUser(Provider provider) {
        this.provider = provider;
    }

    public static ProviderAUser of() {
        return new ProviderAUser(Providers.of("ProviderA"));
    }

    @Override
    public String getProviderType() {
        return this.provider.type();
    }
}
