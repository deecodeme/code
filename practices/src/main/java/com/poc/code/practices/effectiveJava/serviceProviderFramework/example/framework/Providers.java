package com.poc.code.practices.effectiveJava.serviceProviderFramework.example.framework;

import java.util.HashMap;
import java.util.Map;

/*
There are three essential components in a service provider framework: a service interface, which represents an
implementation; a provider registration API, which providers use to register implementations; and a service access API,
 which clients use to obtain instances of the service. The service access API may
         allow clients to specify criteria for choosing an implementation. In the absence of
         such criteria, the API returns an instance of a default implementation, or allows
         the client to cycle through all available implementations. The service access API
         is the flexible static factory that forms the basis of the service provider framework.


      An optional fourth component of a service provider framework is a service provider interface, which describes a
       factory object that produce instances of the service interface.
         In the absence of a service provider interface, implementations must be instantiated
         reflectively (Item 65). In the case of JDBC, Connection plays the part of the service interface, DriverManager.
         registerDriver is the provider registration API, DriverManager.getConnection is the service access API,
          and Driver is the service provider interface.

Excerpt From
Effective Java, Third Edition
Joshua Bloch
This material may be protected by copyright.
 */
public class Providers {
    private static final Map<String, Provider> providers = new HashMap<>();

    private Providers() {
    }

    public static Provider of(String providerName) {
        if (providers.containsKey(providerName)) {
            return providers.get(providerName);
        }
        throw new IllegalArgumentException("No such provider exist");
    }

    public static void register(String providerName, Provider serviceProvider) {
        providers.put(providerName, serviceProvider);
    }
}
