package app.server.storage.repository.history.being.community;

import app.server.model.being.community.Community;
import app.server.model.history.being.community.CommunityHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.being.MongoDbBeingHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public abstract class MongoDbCommunityHistoryRepository<T extends Community, S extends CommunityHistory<T>> extends MongoDbBeingHistoryRepository<T, S> implements CommunityHistoryRepository<T, S> {
    public MongoDbCommunityHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}