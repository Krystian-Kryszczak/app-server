package app.server.storage.repository.report.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.model.report.exhibit.ExhibitReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.MongoDbReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public class MongoDbExhibitReportRepository<T extends Exhibit<T>, S extends ExhibitReport<T>> extends MongoDbReportRepository<S> {
    public MongoDbExhibitReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
}