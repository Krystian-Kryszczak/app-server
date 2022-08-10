package app.server.storage.repository.report.media.image;

import app.server.model.media.image.Image;
import app.server.model.report.media.image.ImageReport;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.report.media.MongoDbMediaReportRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbImageReportRepository extends MongoDbMediaReportRepository<Image, ImageReport> implements ImageReportRepository {
    public MongoDbImageReportRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getReportImageCollection(), ImageReport.class);
    }
}