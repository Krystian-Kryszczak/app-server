package app.server.storage.repository.history.being.user;

import app.server.model.being.user.User;
import app.server.model.history.being.user.UserHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.being.MongoDbBeingHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbUserHistoryRepository extends MongoDbBeingHistoryRepository<User, UserHistory> implements UserHistoryRepository {
    public MongoDbUserHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getUserHistoryCollection(), UserHistory.class);
    }
}