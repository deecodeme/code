package com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.vendors;

import com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.framework.Connection;
import com.poc.code.practices.effectiveJava.serviceProviderFramework.jdbc.framework.Driver;

class PostgreSQLDriver implements Driver {
    private static final String TYPE = "POSTGRESQL";
    private static final PostgreSQLDriver INSTANCE = new PostgreSQLDriver();
    private static final PostgreSQL DB_CONN_INSTANCE = new PostgreSQL();

    private PostgreSQLDriver() {
    }

    public static PostgreSQLDriver of() {
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
