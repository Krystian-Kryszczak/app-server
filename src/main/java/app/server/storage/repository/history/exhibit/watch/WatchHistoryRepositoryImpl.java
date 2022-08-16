package app.server.storage.repository.history.exhibit.watch;

import app.server.model.exhibit.watch.Watch;
import app.server.model.history.exhibit.watch.WatchHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.exhibit.MongoDbExhibitHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class WatchHistoryRepositoryImpl extends MongoDbExhibitHistoryRepository<Watch, WatchHistory> implements WatchHistoryRepository {
    public WatchHistoryRepositoryImpl(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getWatchHistoryCollection(), WatchHistory.class);
    }
}