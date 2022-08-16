package app.server.storage.repository.history.exhibit.reel;

import app.server.model.exhibit.reel.Reel;
import app.server.model.history.exhibit.reel.ReelHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.exhibit.MongoDbExhibitHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class ReelHistoryRepositoryImpl extends MongoDbExhibitHistoryRepository<Reel, ReelHistory> implements ReelHistoryRepository {
    public ReelHistoryRepositoryImpl(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReelHistoryCollection(), ReelHistory.class);
    }
}