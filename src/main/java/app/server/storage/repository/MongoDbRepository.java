package app.server.storage.repository;

import app.server.storage.MongoDbConfiguration;
import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.core.annotation.NonNull;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public abstract class MongoDbRepository {
    private final MongoDbConfiguration mongoConf;
    private final MongoClient mongoClient;
    public MongoDbRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        this.mongoConf = mongoConf;
        this.mongoClient = mongoClient;
    }
    public MongoDbConfiguration getMongoConf() {
        return mongoConf;
    }
    public MongoClient getMongoClient() {
        return mongoClient;
    }
    protected Bson getBsonEq_id(@NonNull String hexId) {
        return eq("_id",  new ObjectId(hexId));
    }
}