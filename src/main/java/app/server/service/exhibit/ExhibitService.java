package app.server.service.exhibit;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

public interface ExhibitService<T extends Exhibit<T>> {
    Mono<Integer> vote(@NonNull User user, @NonNull T exhibit, boolean up) throws NullPointerException;
    Mono<Integer> vote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId, boolean up);
    Mono<Boolean> getVote(@NonNull User user, @NonNull T exhibit) throws NullPointerException;
    Mono<Boolean> getVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId);
    Mono<Integer> cancelVote(@NonNull User user, @NonNull T exhibit) throws NullPointerException;
    Mono<Integer> cancelVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId);
    // ---------------------------------------------------------------------------------------------------- //
    Mono<Void> historyUserVote(String userHexId, String postHexId, boolean up);
    Mono<Void> historyUserCancelVote(String userHexId, String postHexId);
}