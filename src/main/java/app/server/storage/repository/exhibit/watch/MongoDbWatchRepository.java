package app.server.storage.repository.exhibit.watch;

import app.server.model.exhibit.watch.Watch;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.exhibit.ExhibitMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbWatchRepository extends ExhibitMongoDbRepository<Watch> implements WatchRepository {
    public MongoDbWatchRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getWatchCollection(), Watch.class);
    }
}