package app.server.storage.repository.report;

import app.server.model.report.Report;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public abstract class MongoDbReportRepository<T extends Report> extends ExtendedMongoDbRepository<T> {
    public MongoDbReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}