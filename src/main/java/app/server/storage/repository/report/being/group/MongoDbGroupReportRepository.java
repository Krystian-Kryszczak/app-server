package app.server.storage.repository.report.being.group;

import app.server.model.being.group.Group;
import app.server.model.report.being.group.GroupReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.being.MongoDbBeingReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbGroupReportRepository extends MongoDbBeingReportRepository<Group, GroupReport> implements GroupReportRepository {
    public MongoDbGroupReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportGroupCollection(), GroupReport.class);
    }
}