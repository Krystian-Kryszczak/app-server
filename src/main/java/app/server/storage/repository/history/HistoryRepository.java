package app.server.storage.repository.history;

import app.server.model.StorageItem;
import app.server.model.history.History;
import app.server.storage.repository.Repository;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.*;

public interface HistoryRepository<T extends StorageItem, S extends History> extends Repository<S> {
    Mono<Void> addHistory(@NonNull S history);
    Flux<S> getHistory(@NonNull String targetHexId, @Positive @Max(value = 12) int limit);
    Flux<S> getHistory(@NonNull String targetHexId, @Positive @Max(value = 12) int limit, @PositiveOrZero @Max(value = 12) int skip);
    Flux<S> getHistory(@NonNull T target, @Positive @Max(value = 12) int limit);
    Flux<S> getHistory(@NonNull T target, @Positive @Max(value = 12) int limit, @PositiveOrZero @Max(value = 12) int skip);
}