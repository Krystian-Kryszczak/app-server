package app.server.storage.repository.votable;

import app.server.model.StorageItem;
import app.server.storage.repository.Repository;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

public interface VotableRepository<T extends StorageItem> extends Repository<T> {
    @NonNull
    Mono<Integer> setUserVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId, boolean up);
    @NonNull
    Mono<Integer> unsetUserVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId);
    @NonNull
    Mono<Boolean> getVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId);
}