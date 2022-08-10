package app.server.storage.repository.report.media.video;

import app.server.model.media.video.Video;
import app.server.model.report.media.video.VideoReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.media.MongoDbMediaReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbVideoReportRepository extends MongoDbMediaReportRepository<Video, VideoReport> implements VideoReportRepository {
    public MongoDbVideoReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportVideoCollection(), VideoReport.class);
    }
}