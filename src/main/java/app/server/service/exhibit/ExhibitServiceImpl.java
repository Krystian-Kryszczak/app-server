package app.server.service.exhibit;

import app.server.model.comment.Comment;
import app.server.model.comment.sorting.SortBy;
import app.server.model.exhibit.Exhibit;
import app.server.model.exhibit.ExhibitType;
import app.server.model.exhibit.MultiMediaExhibit;
import app.server.service.ServiceConfiguration;
import app.server.service.comment.CommentService;
import app.server.service.votable.VotableServiceImpl;
import app.server.storage.repository.being.user.friends.UserFriendsRepository;
import app.server.storage.repository.exhibit.ExhibitRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public abstract class ExhibitServiceImpl<T extends Exhibit<T>> extends VotableServiceImpl<T> implements ExhibitService<T> {
    @Inject
    static ServiceConfiguration serviceConfig;
    static int limit = serviceConfig.getDefaultFeedLimit();
    @Inject
    private CommentService commentService;
    @Inject
    private UserFriendsRepository userFriendsRepo;
    // ---------------------------------------------------------------------------------------------------- //
    public abstract Mono<Void> historyUserVote(String userHexId, String exhibitHexId, boolean up);
    public abstract Mono<Void> historyUserCancelVote(String userHexId, String exhibitHexId);
    // ---------------------------------------------------------------------------------------------------- //
    public Flux<T> getFeedForUser(@NonNull String userHexId) {
        return getFeedForUser(userHexId, limit, 0);
    }
    public Flux<T> getFeedForUser(@NonNull String userHexId, int skip) {
        return getFeedForUser(userHexId, limit, skip);
    }
    public Flux<T> getFeedForUser(@NonNull String userHexId, int limit, int skip) {
        return getRepo().getFeedForUser(userHexId, limit, skip);
    }
    public Mono<T> getForUser(@NonNull String exhibitHexId) {
        return Mono.from(getRepo().findById(exhibitHexId));
    }
    public Mono<Boolean> uploadByUser(T exhibit, @NonNull String userHexId) {
        return getRepo().uploadByUser(exhibit, userHexId);
    }
    public Mono<Boolean> deleteByUser(@NonNull String exhibitHexId, @NonNull String userHexId) {
        return Mono.from(getRepo().findById(exhibitHexId))
            .map(exhibit -> exhibit.getUserHexId().equals(userHexId))
                .flatMap(result -> result?Mono.from(getRepo().delete(exhibitHexId)).thenReturn(true):Mono.just(false));
    }
    // ---------------------------------------------------------------------------------------------------- //
    public Mono<Boolean> needMedia(@NonNull String exhibitHexId, ExhibitType type) {
        return Mono.from(getRepo().findDocumentById(exhibitHexId)).map(doc ->
            type.getClazz().isAssignableFrom(MultiMediaExhibit.class) ? doc.getList("media", String.class).contains("")
                : doc.getString("media").equals(type.getUrlModifier()));
    }
    public Mono<Boolean> isCompleted(@NonNull String exhibitHexId) {
        return Mono.from(getRepo().findDocumentById(exhibitHexId)).map(doc -> doc.get("media", null)!=null);
    }
    // ---------------------------------------------------------------------------------------------------- //
    public Flux<Comment> getComments(@NonNull String userHexId, @NonNull String exhibitHexId, @Nullable SortBy sortBy) {
        return commentService.find(userHexId, exhibitHexId);
    }
    public Mono<Boolean> addComment(@NonNull String userHexId, @NonNull ExhibitType type, @NonNull String exhibitHexId, @NonNull @NotBlank String content) {
        return commentService.save(new Comment(userHexId, type, exhibitHexId, content));
    }
    public Mono<Boolean> addNodeComment(@NonNull String userHexId, @NonNull String commentHexId, @NonNull @NotBlank String content) {
        return commentService.save(new Comment(userHexId, content, commentHexId));
    }
    public Mono<String> editComment(@NonNull String userHexId, @NonNull @NotBlank String commentHexId, @NonNull @NotBlank String newContent) {
        return commentService.edit(userHexId, commentHexId, newContent);
    }
    public Mono<Boolean> deleteComment(@NonNull String userHexId, @NonNull @NotBlank String hexId) {
        return commentService.findById(hexId)
            .map(Comment::getUserHexId)
                .map(id -> id.equals(userHexId))
                    .flatMap(result -> commentService.deleteById(hexId))
                        .map(Objects::nonNull);
    }
    public Mono<Boolean> hideComment(@NonNull String userHexId, @NonNull @NotBlank String hexId) {
        return commentService.hide(userHexId, hexId);
    }
    public Mono<Boolean> showComment(@NonNull String userHexId, @NonNull @NotBlank String hexId) {
        return commentService.show(userHexId, hexId);
    }
    // ---------------------------------------------------------------------------------------------------- //
    protected abstract ExhibitRepository<T> getRepo();
}