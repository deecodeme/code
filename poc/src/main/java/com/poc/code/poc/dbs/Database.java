package com.poc.code.poc.dbs;

public interface Database {
    DB name();

    <V> V insert(V data);
}
