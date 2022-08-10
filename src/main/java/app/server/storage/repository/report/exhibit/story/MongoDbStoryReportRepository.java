package app.server.storage.repository.report.exhibit.story;

import app.server.model.exhibit.story.Story;
import app.server.model.report.exhibit.story.StoryReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.exhibit.MongoDbExhibitReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbStoryReportRepository extends MongoDbExhibitReportRepository<Story, StoryReport> implements StoryReportRepository {
    public MongoDbStoryReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportStoryCollection(), StoryReport.class);
    }
}