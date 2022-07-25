package app.server.storage.repository.exhibit.music.album;

import app.server.model.exhibit.song.Song;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.exhibit.ExhibitMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbAlbumRepository extends ExhibitMongoDbRepository<Song> implements AlbumRepository {
    public MongoDbAlbumRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getAlbumCollection(), Song.class);
    }
}