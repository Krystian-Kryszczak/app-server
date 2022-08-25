package app.server.storage.repository.history.being;

import app.server.model.being.Being;
import app.server.model.history.History;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.MongoDbHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public class MongoDbBeingHistoryRepository<T extends Being, S extends History> extends MongoDbHistoryRepository<T, S> implements BeingHistoryRepository<T, S> {
    public MongoDbBeingHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}