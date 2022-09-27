package com.poc.code.effectiveJava.serviceProviderFramework.jdbc.vendors;

import com.poc.code.effectiveJava.serviceProviderFramework.jdbc.framework.Connection;
import com.poc.code.effectiveJava.serviceProviderFramework.jdbc.framework.Driver;

class OracleDriver implements Driver {
    private static final String TYPE = "ORACLE";
    private static final OracleDriver INSTANCE = new OracleDriver();
    private static final Oracle DB_CONN_INSTANCE = new Oracle();

    private OracleDriver() {
    }

    public static OracleDriver of() {
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
