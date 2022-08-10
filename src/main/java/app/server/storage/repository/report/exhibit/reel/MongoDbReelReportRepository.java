package app.server.storage.repository.report.exhibit.reel;

import app.server.model.exhibit.reel.Reel;
import app.server.model.report.exhibit.reel.ReelReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.exhibit.MongoDbExhibitReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbReelReportRepository extends MongoDbExhibitReportRepository<Reel, ReelReport> implements ReelReportRepository {
    public MongoDbReelReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportReelCollection(), ReelReport.class);
    }
}