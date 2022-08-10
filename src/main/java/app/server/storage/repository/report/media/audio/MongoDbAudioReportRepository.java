package app.server.storage.repository.report.media.audio;

import app.server.model.media.audio.Audio;
import app.server.model.report.media.audio.AudioReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.media.MongoDbMediaReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbAudioReportRepository extends MongoDbMediaReportRepository<Audio, AudioReport> implements AudioReportRepository {
    public MongoDbAudioReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportAudioCollection(), AudioReport.class);
    }
}