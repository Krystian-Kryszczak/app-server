package app.server.storage.repository.media;

import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public abstract class MediaMongoDbRepository<T> extends ExtendedMongoDbRepository<T> {
    public MediaMongoDbRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}