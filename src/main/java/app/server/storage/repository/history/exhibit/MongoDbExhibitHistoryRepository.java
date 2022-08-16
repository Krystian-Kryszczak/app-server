package app.server.storage.repository.history.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.model.history.exhibit.ExhibitHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.MongoDbHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public class MongoDbExhibitHistoryRepository<T extends Exhibit<T>, S extends ExhibitHistory<T>> extends MongoDbHistoryRepository<T, S> implements ExhibitHistoryRepository<T, S> {
    public MongoDbExhibitHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}