package app.server.service.exhibit;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import app.server.storage.repository.exhibit.ExhibitRepository;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

public abstract class ExhibitServiceImpl<T extends Exhibit<T>> implements ExhibitService<T> {
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public Mono<Integer> vote(@NonNull User user, @NonNull T exhibit, boolean up) throws NullPointerException {
        String userHexId = user.getId().toHexString(),
        exhibitHexId = exhibit.getId().toHexString();
        if (userHexId==null) throw new NullPointerException("User hexId equal null!!");
        if (exhibitHexId==null) throw new NullPointerException("Exhibit hexId equal null!!");
        return vote(userHexId, exhibitHexId, up);
    }
    @Override
    public Mono<Integer> vote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId, boolean up) {
        return getRepo().setUserVote(userHexId, exhibitHexId, up).flatMap(rating ->
            // -------------------------------------------------- //
            historyUserVote(userHexId, exhibitHexId, up).map(v -> rating) // add userHistory
            // -------------------------------------------------- //
        );
    }
    @Override
    public Mono<Boolean> getVote(@NonNull User user, @NonNull T exhibit) throws NullPointerException {
        String userHexId = user.getId().toHexString(),
        exhibitHexId = exhibit.getId().toHexString();
        if (userHexId==null) throw new NullPointerException("User hexId equal null!!");
        if (exhibitHexId==null) throw new NullPointerException("Exhibit hexId equal null!!");
        return getVote(userHexId, exhibitHexId);
    }
    @Override
    public Mono<Boolean> getVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId) {
        return getRepo().getVote(userHexId, exhibitHexId);
    }
    @Override
    public Mono<Integer> cancelVote(@NonNull User user, @NonNull T exhibit) throws NullPointerException {
        String userHexId = user.getId().toHexString(),
        exhibitHexId = exhibit.getId().toHexString();
        if (userHexId==null) throw new NullPointerException("User hexId equal null!!");
        if (exhibitHexId==null) throw new NullPointerException("Exhibit hexId equal null!!");
        return cancelVote(userHexId, exhibitHexId);
    }
    @Override
    public Mono<Integer> cancelVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String exhibitHexId) {
        return getRepo().unsetUserVote(userHexId, exhibitHexId).flatMap(rating ->
            // -------------------------------------------------- //
            historyUserCancelVote(userHexId, exhibitHexId).map(v -> rating) // add userHistory
            // -------------------------------------------------- //
        );
    }
    // ---------------------------------------------------------------------------------------------------- //
    public abstract Mono<Void> historyUserVote(String userHexId, String exhibitHexId, boolean up);
    public abstract Mono<Void> historyUserCancelVote(String userHexId, String exhibitHexId);
    // ---------------------------------------------------------------------------------------------------- //
    protected abstract ExhibitRepository<T> getRepo();
}