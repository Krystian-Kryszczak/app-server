package app.server.storage.repository.media.video;

import app.server.model.media.video.Video;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.media.MediaMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbVideoRepository extends MediaMongoDbRepository<Video> implements VideoRepository {
    public MongoDbVideoRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getVideoCollection(), Video.class);
    }
}