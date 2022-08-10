package app.server.storage.repository.report.exhibit.post;

import app.server.model.exhibit.post.Post;
import app.server.model.report.exhibit.post.PostReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.exhibit.MongoDbExhibitReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbPostReportRepository extends MongoDbExhibitReportRepository<Post, PostReport> implements PostReportRepository {
    public MongoDbPostReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportPostCollection(), PostReport.class);
    }
}