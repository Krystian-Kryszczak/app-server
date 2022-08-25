package app.server.storage.repository.history.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.model.history.being.community.group.GroupHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.being.community.MongoDbCommunityHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbGroupHistoryRepository extends MongoDbCommunityHistoryRepository<Group, GroupHistory> implements GroupHistoryRepository {
    public MongoDbGroupHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getGroupHistoryCollection(), GroupHistory.class);
    }
}