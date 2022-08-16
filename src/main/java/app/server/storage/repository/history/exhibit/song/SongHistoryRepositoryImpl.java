package app.server.storage.repository.history.exhibit.song;

import app.server.model.exhibit.song.Song;
import app.server.model.history.exhibit.song.SongHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.exhibit.MongoDbExhibitHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class SongHistoryRepositoryImpl extends MongoDbExhibitHistoryRepository<Song, SongHistory> implements SongHistoryRepository {
    public SongHistoryRepositoryImpl(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getSongHistoryCollection(), SongHistory.class);
    }
}