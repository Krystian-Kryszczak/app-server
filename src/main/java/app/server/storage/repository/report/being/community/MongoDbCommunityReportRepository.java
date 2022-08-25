package app.server.storage.repository.report.being.community;

import app.server.model.being.community.Community;
import app.server.model.report.being.community.CommunityReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.being.MongoDbBeingReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public class MongoDbCommunityReportRepository<T extends Community, S extends CommunityReport<T>> extends MongoDbBeingReportRepository<T, S> {
    public MongoDbCommunityReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}