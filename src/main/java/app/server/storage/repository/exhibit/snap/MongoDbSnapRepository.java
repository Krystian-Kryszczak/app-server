package app.server.storage.repository.exhibit.snap;

import app.server.model.exhibit.snap.Snap;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.exhibit.ExhibitMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbSnapRepository extends ExhibitMongoDbRepository<Snap> implements SnapRepository {
    public MongoDbSnapRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getSnapCollection(), Snap.class);
    }
}