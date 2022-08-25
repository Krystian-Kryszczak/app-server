package app.server.storage.repository.being.community;

import app.server.model.being.community.Community;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.being.MongoDbBeingRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public abstract class MongoDbCommunityRepository<T extends Community> extends MongoDbBeingRepository<T> implements CommunityRepository<T> {
    public MongoDbCommunityRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}