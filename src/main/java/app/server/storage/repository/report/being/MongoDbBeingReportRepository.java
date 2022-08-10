package app.server.storage.repository.report.being;

import app.server.model.being.Being;
import app.server.model.report.being.BeingReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.MongoDbReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public class MongoDbBeingReportRepository<T extends Being, S extends BeingReport<T>> extends MongoDbReportRepository<S> {
    public MongoDbBeingReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}