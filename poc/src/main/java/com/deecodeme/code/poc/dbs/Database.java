package com.deecodeme.code.poc.dbs;

public interface Database {
    DB name();

    <V> V insert(V data);
}
