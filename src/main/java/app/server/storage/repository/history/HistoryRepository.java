package app.server.storage.repository.history;

import app.server.model.being.Being;
import app.server.model.history.History;
import app.server.storage.repository.Repository;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public interface HistoryRepository<T extends History, V extends Being> extends Repository<T> {
    Mono<Void> addHistory(@NonNull @NotBlank String hexId, @NonNull T history);
    Mono<Void> addHistory(@NonNull V being, @NonNull T history);
    Flux<T> getHistory(@NonNull String hexId, @Min(value = 1) @Max(value = 12) int limit);
    Flux<T> getHistory(@NonNull String hexId, @Min(value = 1) @Max(value = 12) int limit, @Min(value = 0) @Max(value = 12) int skip);
    Flux<T> getHistory(@NonNull V being, @Min(value = 1) @Max(value = 12) int limit);
    Flux<T> getHistory(@NonNull V being, @Min(value = 1) @Max(value = 12) int limit, @Min(value = 0) @Max(value = 12) int skip);
}