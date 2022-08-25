package app.server.service.comment;

import app.server.model.being.user.User;
import app.server.model.comment.Comment;
import app.server.service.votable.VotableService;
import io.micronaut.core.annotation.NonNull;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService extends VotableService<Comment> {
    Mono<Boolean> save(Comment comment);
    Flux<Comment> find(String userHexId, String exhibitHexId);
    Mono<Comment> findById(String hexId);
    Mono<Comment> deleteById(String HexId);
    Mono<String> edit(User user, ObjectId id, String newContent);
    Mono<String> edit(String userHexId, ObjectId id, String newContent);
    Mono<String> edit(String userHexId, String commentHexId, String newContent);
    Mono<Boolean> hide(@NonNull User user, @NonNull String hexId);
    Mono<Boolean> hide(@NonNull String userHexId, @NonNull String hexId);
    Mono<Boolean> show(@NonNull User user, @NonNull String hexId);
    Mono<Boolean> show(@NonNull String userHexId, @NonNull String hexId);
}