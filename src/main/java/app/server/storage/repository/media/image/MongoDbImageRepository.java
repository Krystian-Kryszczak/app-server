package app.server.storage.repository.media.image;

import app.server.model.media.image.Image;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.media.MediaMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbImageRepository extends MediaMongoDbRepository<Image> implements ImageRepository {
    public MongoDbImageRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getImageCollection(), Image.class);
    }
}