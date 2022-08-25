package app.server.service.comment;

import app.server.model.being.user.User;
import app.server.model.comment.Comment;
import app.server.service.history.being.user.UserHistoryService;
import app.server.service.votable.VotableServiceImpl;
import app.server.storage.repository.comment.CommentRepository;
import app.server.storage.repository.votable.VotableRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
public class CommentServiceImpl extends VotableServiceImpl<Comment> implements CommentService {
    final CommentRepository commentRepository;
    final UserHistoryService historyService;
    public CommentServiceImpl(CommentRepository commentRepository, UserHistoryService historyService) {
        this.commentRepository = commentRepository;
        this.historyService = historyService;
    }
    public Mono<Boolean> save(Comment comment) {
        return Mono.from(commentRepository.save(comment)).thenReturn(true).onErrorReturn(false);
    }
    public Flux<Comment> find(String userHexId, String exhibitHexId) {
        if (!ObjectId.isValid(userHexId)) return Flux.empty();
        return Flux.from(commentRepository.findByExhibitHexId(exhibitHexId));
    }
    public Mono<Comment> findById(String hexId) {
        return Mono.from(commentRepository.findById(hexId));
    }
    public Mono<Comment> deleteById(String hexId) {
        return Mono.from(commentRepository.delete(hexId));
    }
    public Mono<String> edit(@NonNull User user, @NonNull ObjectId id, @NonNull String newContent) {
        if (user.getId()==null) return Mono.empty();
        return edit(user.getId().toHexString(), id.toHexString(), newContent);
    }
    public Mono<String> edit(String userHexId, @NonNull ObjectId id, String newContent) {
        return edit(userHexId, id.toHexString(), newContent);
    }
    public Mono<String> edit(@NonNull String userHexId, @NonNull String commentHexId, @NonNull String newContent) {
        return Mono.from(commentRepository.findById(commentHexId))
                .map(comment -> comment.getUserHexId().equals(userHexId))
                    .flatMap(result -> result?commentRepository.edit(commentHexId, newContent):Mono.empty());
    }
    public Mono<Boolean> hide(@NonNull User user, @NonNull String hexId) {
        if (user.getId()==null) return Mono.just(false);
        return hide(user.getId().toHexString(), hexId);
    }
    public Mono<Boolean> hide(@NonNull String userHexId, @NonNull String hexId) {
        return findById(hexId)
            .map(Comment::getUserHexId)
                .map(id -> id.equals(userHexId))
                    .flatMap(result -> result?commentRepository.hide(hexId):Mono.just(false));
    }
    public Mono<Boolean> show(@NonNull User user, @NonNull String hexId) {
        if (user.getId()==null) return Mono.just(false);
        return show(user.getId().toHexString(), hexId);
    }
    public Mono<Boolean> show(@NonNull String userHexId, @NonNull String hexId) {
        return findById(hexId)
            .map(Comment::getUserHexId)
                .map(id -> id.equals(userHexId))
                    .flatMap(result -> result?commentRepository.show(hexId):Mono.just(false));
    }
    public Mono<Void> historyUserVote(String userHexId, String targetHexId, boolean up) {
        return historyService.userVoteComment(userHexId, targetHexId, up);
    }
    public Mono<Void> historyUserCancelVote(String userHexId, String targetHexId) {
        return historyService.userCancelVoteComment(userHexId, targetHexId);
    }
    protected VotableRepository<Comment> getRepo() {
        return commentRepository;
    }
}