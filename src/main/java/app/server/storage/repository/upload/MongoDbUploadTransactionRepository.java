package app.server.storage.repository.upload;

import app.server.model.upload.UploadTransaction;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbUploadTransactionRepository extends ExtendedMongoDbRepository<UploadTransaction> implements UploadTransactionRepository {
    public MongoDbUploadTransactionRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getUploadTransactionCollection(), UploadTransaction.class);
    }
}