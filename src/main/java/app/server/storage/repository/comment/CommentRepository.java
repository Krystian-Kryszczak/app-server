package app.server.storage.repository.comment;

import app.server.model.comment.Comment;
import app.server.storage.repository.votable.VotableRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentRepository extends VotableRepository<Comment> {
    Flux<Comment> findByExhibitHexId(String hexId);
    Mono<String> edit(String commentHexId, String newContent);
    Mono<Boolean> hide(String hexId);
    Mono<Boolean> show(String hexId);
}