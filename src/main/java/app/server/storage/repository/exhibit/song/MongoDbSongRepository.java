package app.server.storage.repository.exhibit.song;

import app.server.model.exhibit.song.Song;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.exhibit.ExhibitMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbSongRepository extends ExhibitMongoDbRepository<Song> implements SongRepository {
    public MongoDbSongRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getSongCollection(), Song.class);
    }
}