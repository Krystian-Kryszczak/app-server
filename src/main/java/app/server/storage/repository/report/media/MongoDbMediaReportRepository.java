package app.server.storage.repository.report.media;

import app.server.model.media.Media;
import app.server.model.report.media.MediaReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.MongoDbReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public abstract class MongoDbMediaReportRepository<T extends Media<T>, S extends MediaReport<T>> extends MongoDbReportRepository<S> {
    public MongoDbMediaReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}