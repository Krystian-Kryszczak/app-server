package app.server.service.votable;

import app.server.model.StorageItem;
import app.server.model.being.user.User;
import app.server.storage.repository.votable.VotableRepository;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

public abstract class VotableServiceImpl<T extends StorageItem> implements VotableService<T> {
    public Mono<Integer> vote(@NonNull User user, @NonNull T target, boolean up) throws NullPointerException {
        if (user.getId()==null) throw new NullPointerException("User hexId equal null!!");
        if (target.getId()==null) throw new NullPointerException("Target hexId equal null!!");
        String userHexId = user.getId().toHexString(),
                exhibitHexId = target.getId().toHexString();
        return vote(userHexId, exhibitHexId, up);
    }
    public Mono<Integer> vote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId, boolean up) {
        return getRepo().setUserVote(userHexId, targetHexId, up).flatMap(rating ->
            // -------------------------------------------------- //
                historyUserVote(userHexId, targetHexId, up).map(v -> rating) // add userHistory
            // -------------------------------------------------- //
        );
    }
    public Mono<Boolean> getVote(@NonNull User user, @NonNull T target) throws NullPointerException {
        if (user.getId()==null) throw new NullPointerException("User hexId equal null!!");
        if (target.getId()==null) throw new NullPointerException("Target hexId equal null!!");
        String userHexId = user.getId().toHexString(),
        exhibitHexId = target.getId().toHexString();
        return getVote(userHexId, exhibitHexId);
    }
    public Mono<Boolean> getVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId) {
        return getRepo().getVote(userHexId, targetHexId);
    }
    public Mono<Integer> cancelVote(@NonNull User user, @NonNull T target) throws NullPointerException {
        if (user.getId()==null) throw new NullPointerException("User hexId equal null!!");
        if (target.getId()==null) throw new NullPointerException("Target hexId equal null!!");
        String userHexId = user.getId().toHexString(),
        exhibitHexId = target.getId().toHexString();
        return cancelVote(userHexId, exhibitHexId);
    }
    public Mono<Integer> cancelVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId) {
        return getRepo().unsetUserVote(userHexId, targetHexId).flatMap(rating ->
            // -------------------------------------------------- //
                historyUserCancelVote(userHexId, targetHexId).map(v -> rating) // add userHistory
            // -------------------------------------------------- //
        );
    }
    public abstract Mono<Void> historyUserVote(String userHexId, String targetHexId, boolean up);
    public abstract Mono<Void> historyUserCancelVote(String userHexId, String targetHexId);
    protected abstract VotableRepository<T> getRepo();
}