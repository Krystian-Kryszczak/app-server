package app.server.storage.repository.history.group;

import app.server.model.being.group.Group;
import app.server.model.history.group.GroupHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.HistoryMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbGroupHistoryRepository extends HistoryMongoDbRepository<GroupHistory, Group> implements GroupHistoryRepository {
    public MongoDbGroupHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getGroupHistoryCollection(), GroupHistory.class);
    }
}