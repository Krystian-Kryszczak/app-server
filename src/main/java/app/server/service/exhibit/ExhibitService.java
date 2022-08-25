package app.server.service.exhibit;

import app.server.model.comment.Comment;
import app.server.model.comment.sorting.SortBy;
import app.server.model.exhibit.Exhibit;
import app.server.model.exhibit.ExhibitType;
import app.server.service.votable.VotableService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

public interface ExhibitService<T extends Exhibit<T>> extends VotableService<T> {
    Flux<T> getFeedForUser(@NonNull String userHexId);
    Flux<T> getFeedForUser(@NonNull String userHexId, int skip);
    Flux<T> getFeedForUser(@NonNull String userHexId, int limit, int skip);
    Mono<T> getForUser(@NonNull String exhibitHexId);
    Mono<Boolean> uploadByUser(T exhibit, String clientHexId);
    Mono<Boolean> deleteByUser(@NonNull String exhibitHexId, @NonNull String userHexId);
    // ---------------------------------------------------------------------------------------------------- //
    Mono<Boolean> needMedia(@NonNull String exhibitHexId, ExhibitType type);
    Mono<Boolean> isCompleted(@NonNull String exhibitHexId);
    // ---------------------------------------------------------------------------------------------------- //
    Flux<Comment> getComments(@NonNull String userHexId, @NonNull String exhibitHexId, @Nullable SortBy sortBy);
    Mono<Boolean> addComment(@NonNull String userHexId, @NonNull ExhibitType type, @NonNull String exhibitHexId, @NonNull @NotBlank String content);
    Mono<Boolean> addNodeComment(@NonNull String userHexId, @NonNull String commentHexId, @NonNull @NotBlank String content);
    Mono<String> editComment(@NonNull String userHexId, @NonNull @NotBlank String commentHexId, @NonNull @NotBlank String newContent);
    Mono<Boolean> deleteComment(@NonNull String userHexId, @NonNull @NotBlank String hexId);
    Mono<Boolean> hideComment(@NonNull String userHexId, @NonNull @NotBlank String hexId);
}