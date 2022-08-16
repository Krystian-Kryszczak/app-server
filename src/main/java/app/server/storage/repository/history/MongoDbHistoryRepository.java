package app.server.storage.repository.history;

import app.server.model.StorageItem;
import app.server.model.history.History;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.client.model.Updates;
import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.core.annotation.NonNull;
import org.bson.conversions.Bson;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

public class MongoDbHistoryRepository<T extends StorageItem, S extends History> extends ExtendedMongoDbRepository<S> implements HistoryRepository<T, S> {
    public MongoDbHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<S> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public Mono<Void> addHistory(@NonNull @NotBlank String userHexId, @NonNull S history) {
        Bson updates = Updates.addToSet("history", history); //Updates.combine(Updates.addToSet("history", history));
        return Mono.from(getCollection().findOneAndUpdate(eq("_id", userHexId), updates)).thenEmpty(Mono.empty());
    }
    @Override
    public Mono<Void> addHistory(@NonNull T being, @NonNull S history) throws NullPointerException {
        if (being.getId()==null) throw new NullPointerException("Being don,t have hexId!");
        return addHistory(being.getId().toHexString(), history);
    }
    @Override
    public Flux<S> getHistory(@NonNull String hexId, @Min(value = 1) @Max(value = 12) int limit) {
        return getHistory(hexId, limit, 0);
    }
    @Override
    public Flux<S> getHistory(@NonNull String hexId, @Min(value = 1) @Max(value = 12) int limit, @PositiveOrZero @Max(value = 12) int skip) {
        Bson filter = eq("userId", hexId); // equal
        return Flux.from(getCollection().find(filter)
                .sort(descending("datetime"))
                .skip(skip)
                .limit(limit));
    }
    @Override
    public Flux<S> getHistory(@NonNull T being, @Min(value = 1) @Max(value = 12) int limit) {
        return getHistory(being, limit, 0);
    }
    @Override
    public Flux<S> getHistory(@NonNull T being, @Min(value = 1) @Max(value = 12) int limit, @Min(value = 0) @Max(value = 12) int skip) {
        if (being.getId()==null) return Flux.empty();
        return getHistory(being.getId().toHexString(), limit, skip);
    }
}