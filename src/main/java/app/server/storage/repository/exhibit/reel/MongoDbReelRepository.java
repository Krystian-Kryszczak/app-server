package app.server.storage.repository.exhibit.reel;

import app.server.model.exhibit.reel.Reel;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.exhibit.ExhibitMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbReelRepository extends ExhibitMongoDbRepository<Reel> implements ReelRepository {
    public MongoDbReelRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReelCollection(), Reel.class);
    }
}