package app.server.storage.repository.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.storage.repository.Repository;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

public interface ExhibitRepository<T extends Exhibit<T>> extends Repository<T> {
    @NonNull
    Mono<Integer> setUserVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId, boolean up);
    @NonNull
    Mono<Integer> unsetUserVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId);
    @NonNull
    Mono<Boolean> getVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId);
    @NonNull
    @Deprecated
    Mono<Integer> incrementRating(@NonNull @NotBlank String exhibitHexId, int value);
}