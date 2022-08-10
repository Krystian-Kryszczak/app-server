package app.server.storage.repository.report.exhibit.snap;

import app.server.model.exhibit.snap.Snap;
import app.server.model.report.exhibit.snap.SnapReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.exhibit.MongoDbExhibitReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbSnapReportRepository extends MongoDbExhibitReportRepository<Snap, SnapReport> implements SnapReportRepository {
    public MongoDbSnapReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportSnapCollection(), SnapReport.class);
    }
}