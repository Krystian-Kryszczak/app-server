package app.server.storage.repository;

import app.server.storage.MongoDbConfiguration;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.micronaut.core.annotation.NonNull;
import org.bson.Document;
import org.reactivestreams.Publisher;

public abstract class ExtendedMongoDbRepository<T> extends MongoDbRepository {
    final String collectionName;
    final Class<T> clazz;
    public ExtendedMongoDbRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient);
        this.collectionName = collectionName;
        this.clazz = clazz;
    }
    public String getCollectionName() {
        return collectionName;
    }
    public Publisher<InsertOneResult> save(@NonNull T item) {
        return getCollection().insertOne(item);
    }
    public Publisher<T> findById(@NonNull String hexId) {
        return getCollection().find(getBsonEq_id(hexId)).first();
    }
    public Publisher<Document> findDocumentById(@NonNull String hexId) {
        return getDocCollection().find(getBsonEq_id(hexId)).first();
    }
    public Publisher<T> delete(@NonNull String hexId) {
        return getCollection().findOneAndDelete(getBsonEq_id(hexId));
    }
    @NonNull
    protected MongoDatabase getDataBase() {
        return getMongoClient().getDatabase(getMongoConf().getName());
    }
    protected MongoCollection<T> getCollection() {
        return getDataBase().getCollection(getCollectionName(), clazz);
    }
    @NonNull
    protected MongoCollection<Document> getDocCollection() {
        return getDataBase().getCollection(this.collectionName);
    }
}