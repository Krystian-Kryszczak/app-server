package app.server.storage.repository.history.being.group;

import app.server.model.being.group.Group;
import app.server.model.history.being.group.GroupHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.being.MongoDbBeingHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbGroupBeingHistoryRepository extends MongoDbBeingHistoryRepository<Group, GroupHistory> implements GroupBeingHistoryRepository {
    public MongoDbGroupBeingHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getGroupHistoryCollection(), GroupHistory.class);
    }
}