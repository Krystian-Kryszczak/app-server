package app.server.storage.repository.report.exhibit.song;

import app.server.model.exhibit.song.Song;
import app.server.model.report.exhibit.song.SongReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.exhibit.MongoDbExhibitReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbSongReportRepository extends MongoDbExhibitReportRepository<Song, SongReport> implements SongReportRepository {
    public MongoDbSongReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportSongCollection(), SongReport.class);
    }
}