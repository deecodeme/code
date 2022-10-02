package com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.client;

import com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.framework.Connection;
import com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.framework.DriverManager;

public class ClientApplication {
    private Connection connection;

    private ClientApplication(Connection connection) {
        this.connection = connection;
    }

    public static ClientApplication withDB(String DB) {
        return new ClientApplication(DriverManager.getConnection(DB));
    }
}
