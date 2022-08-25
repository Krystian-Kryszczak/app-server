package app.server.storage.repository.report.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.model.report.being.community.group.GroupReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.being.community.MongoDbCommunityReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbGroupReportRepository extends MongoDbCommunityReportRepository<Group, GroupReport> implements GroupReportRepository {
    public MongoDbGroupReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportGroupCollection(), GroupReport.class);
    }
}