package app.server.storage.repository.report.exhibit.watch;

import app.server.model.exhibit.watch.Watch;
import app.server.model.report.exhibit.watch.WatchReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.exhibit.MongoDbExhibitReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbWatchReportRepository extends MongoDbExhibitReportRepository<Watch, WatchReport> implements WatchReportRepository {
    public MongoDbWatchReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportWatchCollection(), WatchReport.class);
    }
}