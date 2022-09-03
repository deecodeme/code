package com.poc.code.poc.dbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class DBFactory {
    private final Map<DB, Database> databases;

    @Autowired
    private DBFactory(List<Database> databaseList) {
        this.databases = databaseList.stream().collect(Collectors.toMap(Database::name, vDatabase -> vDatabase));
    }

    public Database getDatabase(DB db) {
        if (!databases.containsKey(db)) {
            throw new NoSuchElementException(String.format("db: %s not supported", db));
        }
        return databases.get(db);
    }
}
