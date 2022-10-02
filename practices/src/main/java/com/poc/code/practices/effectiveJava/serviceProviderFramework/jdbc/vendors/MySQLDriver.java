package com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.vendors;

import com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.framework.Connection;
import com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.framework.Driver;

class MySQLDriver implements Driver {
    private static final String TYPE = "MYSQL";
    private static final MySQLDriver INSTANCE = new MySQLDriver();
    private static final MySQL DB_CONN_INSTANCE = new MySQL();
    private MySQLDriver() {
    }

    public static MySQLDriver of() {
        return INSTANCE;
    }

    @Override
    public boolean isSupportedType(String DB) {
        return TYPE.equals(DB);
    }

    @Override
    public Connection getConnection() {
        return DB_CONN_INSTANCE;
    }
}
