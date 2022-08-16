package app.server.storage.repository.history;

import app.server.model.StorageItem;
import app.server.model.history.History;
import app.server.storage.repository.Repository;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public interface HistoryRepository<T extends StorageItem, S extends History> extends Repository<S> {
    Mono<Void> addHistory(@NonNull @NotBlank String hexId, @NonNull S history);
    Mono<Void> addHistory(@NonNull T being, @NonNull S history);
    Flux<S> getHistory(@NonNull String hexId, @Min(value = 1) @Max(value = 12) int limit);
    Flux<S> getHistory(@NonNull String hexId, @Min(value = 1) @Max(value = 12) int limit, @Min(value = 0) @Max(value = 12) int skip);
    Flux<S> getHistory(@NonNull T being, @Min(value = 1) @Max(value = 12) int limit);
    Flux<S> getHistory(@NonNull T being, @Min(value = 1) @Max(value = 12) int limit, @Min(value = 0) @Max(value = 12) int skip);
}