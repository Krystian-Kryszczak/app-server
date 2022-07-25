package app.server.storage.repository.media.audio;

import app.server.model.media.audio.Audio;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.media.MediaMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbAudioRepository extends MediaMongoDbRepository<Audio> implements AudioRepository {
    public MongoDbAudioRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getAudioCollection(), Audio.class);
    }
}