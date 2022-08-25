package app.server.storage.repository.history;

import app.server.model.StorageItem;
import app.server.model.history.History;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.core.annotation.NonNull;
import org.bson.conversions.Bson;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

public class MongoDbHistoryRepository<T extends StorageItem, S extends History> extends ExtendedMongoDbRepository<S> implements HistoryRepository<T, S> {
    public MongoDbHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public Mono<Void> addHistory(@NonNull S history) {
        return Mono.from(getCollection().insertOne(history)).thenEmpty(Mono.empty());
    }
    @Override
    public Flux<S> getHistory(@NonNull String targetHexId, @Positive @Max(value = 12) int limit) {
        return getHistory(targetHexId, limit, 0);
    }
    @Override
    public Flux<S> getHistory(@NonNull String targetHexId, @Positive @Max(value = 12) int limit, @PositiveOrZero @Max(value = 12) int skip) {
        Bson filter = eq("_id", targetHexId); // equal
        return Flux.from(getCollection().find(filter)
            .sort(descending("datetime"))
            .skip(skip)
            .limit(limit));
    }
    @Override
    public Flux<S> getHistory(@NonNull T target, @Positive @Max(value = 12) int limit) {
        return getHistory(target, limit, 0);
    }
    @Override
    public Flux<S> getHistory(@NonNull T target, @Positive @Max(value = 12) int limit, @PositiveOrZero @Max(value = 12) int skip) {
        if (target.getId()==null) return Flux.empty();
        return getHistory(target.getId().toHexString(), limit, skip);
    }
}