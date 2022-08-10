package app.server.storage.repository.report.exhibit.page;

import app.server.model.exhibit.page.Page;
import app.server.model.report.exhibit.page.PageReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.exhibit.MongoDbExhibitReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbPageReportRepository extends MongoDbExhibitReportRepository<Page, PageReport> implements PageReportRepository {
    public MongoDbPageReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportPageCollection(), PageReport.class);
    }
}