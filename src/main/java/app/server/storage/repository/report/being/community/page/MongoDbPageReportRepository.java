package app.server.storage.repository.report.being.community.page;

import app.server.model.being.community.page.Page;
import app.server.model.report.being.community.page.PageReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.being.community.MongoDbCommunityReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbPageReportRepository extends MongoDbCommunityReportRepository<Page, PageReport> implements PageReportRepository {
    public MongoDbPageReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportPageCollection(), PageReport.class);
    }
}