package app.server.storage.repository.reset;

import app.server.model.reset.ResetCode;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class MongoDbResetPasswordRepository extends ExtendedMongoDbRepository<ResetCode> implements ResetPasswordRepository {
    public MongoDbResetPasswordRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getResetPasswordCollection(), ResetCode.class);
    }
    @NonNull
    @Override
    public Publisher<ResetCode> list() {
        return getCollection().find();
    }
    @Override
    public Publisher<InsertOneResult> save(@NonNull ResetCode resetCode) {
        return getCollection().insertOne(resetCode);
    }
    @NonNull
    @Override
    public Publisher<ResetCode> findById(@NonNull String code) {
        return find(code);
    }
    @NonNull
    @Override
    public Publisher<ResetCode> find(@NonNull String code) {
        return getCollection().find(eq("code", code));
    }
    @Override
    public Publisher<ResetCode> delete(@NonNull String code) {
        return getCollection().findOneAndDelete(eq("code", code));
    }
}