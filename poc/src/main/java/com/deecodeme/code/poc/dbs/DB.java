package com.deecodeme.code.poc.dbs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum DB {
    MONGO,
    HBASE;

    @JsonCreator
    public static DB deSerialise(String dbName) {
        return Arrays.stream(DB.values())
                .filter(db -> db.name().equalsIgnoreCase(dbName))
                .findFirst()
                .orElseThrow();
    }

    @JsonValue
    public String serialise() {
        return this.name();
    }

}
