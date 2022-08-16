package app.server.storage.repository.history.exhibit.snap;

import app.server.model.exhibit.snap.Snap;
import app.server.model.history.exhibit.snap.SnapHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.exhibit.MongoDbExhibitHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class SnapHistoryRepositoryImpl extends MongoDbExhibitHistoryRepository<Snap, SnapHistory> implements SnapHistoryRepository {
    public SnapHistoryRepositoryImpl(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getSnapHistoryCollection(), SnapHistory.class);
    }
}