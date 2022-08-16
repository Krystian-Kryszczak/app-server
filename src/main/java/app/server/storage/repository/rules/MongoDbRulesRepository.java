package app.server.storage.repository.rules;

import app.server.model.rules.Rules;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public abstract class MongoDbRulesRepository<T extends Rules> extends ExtendedMongoDbRepository<T> {
    public MongoDbRulesRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}