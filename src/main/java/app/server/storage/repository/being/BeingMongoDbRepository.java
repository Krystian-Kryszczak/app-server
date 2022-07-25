package app.server.storage.repository.being;

import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class BeingMongoDbRepository<T> extends ExtendedMongoDbRepository<T> {
    public BeingMongoDbRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}