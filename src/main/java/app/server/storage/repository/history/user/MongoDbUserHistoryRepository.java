package app.server.storage.repository.history.user;

import app.server.model.being.user.User;
import app.server.model.history.user.UserHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.HistoryMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbUserHistoryRepository extends HistoryMongoDbRepository<UserHistory, User> implements UserHistoryRepository {
    public MongoDbUserHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getUserHistoryCollection(), UserHistory.class);
    }
}