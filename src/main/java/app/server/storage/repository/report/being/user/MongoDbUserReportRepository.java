package app.server.storage.repository.report.being.user;

import app.server.model.being.user.User;
import app.server.model.report.being.user.UserReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.being.MongoDbBeingReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbUserReportRepository extends MongoDbBeingReportRepository<User, UserReport> implements UserReportRepository {
    public MongoDbUserReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportUserCollection(), UserReport.class);
    }
}