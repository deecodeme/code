package com.poc.code.effectiveJava.serviceProviderFramework.client;

import com.poc.code.effectiveJava.serviceProviderFramework.example.client.ProviderAUser;
import com.poc.code.effectiveJava.serviceProviderFramework.example.client.ProviderUser;
import com.poc.code.effectiveJava.serviceProviderFramework.example.providers.ProviderA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProviderAUserTest {

    @Test
    void getProviderType() {
        ProviderA.INSTANCE.register();
        ProviderUser providerUser = ProviderAUser.of();
        Assertions.assertEquals("ProviderA", providerUser.getProviderType());
    }
}