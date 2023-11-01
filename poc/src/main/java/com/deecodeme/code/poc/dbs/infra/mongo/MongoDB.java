package com.deecodeme.code.poc.dbs.infra.mongo;

import com.deecodeme.code.poc.dbs.DB;
import com.deecodeme.code.poc.dbs.Database;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDB implements Database {

    private MongoTemplate mongoTemplate;

    public MongoDB(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public <V> V insert(V data) {
        return this.mongoTemplate.insert(data, "mongo_entity");
    }

    @Override
    public DB name() {
        return DB.MONGO;
    }
}
