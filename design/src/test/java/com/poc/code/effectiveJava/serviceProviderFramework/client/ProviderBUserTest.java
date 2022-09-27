package com.poc.code.effectiveJava.serviceProviderFramework.client;

import com.poc.code.effectiveJava.serviceProviderFramework.example.client.ProviderBUser;
import com.poc.code.effectiveJava.serviceProviderFramework.example.client.ProviderUser;
import com.poc.code.effectiveJava.serviceProviderFramework.example.providers.ProviderB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProviderBUserTest {

    @Test
    void getProviderType() {
        ProviderB.INSTANCE.register();
        ProviderUser providerUser = ProviderBUser.of();
        Assertions.assertEquals("ProviderB", providerUser.getProviderType());
    }
}