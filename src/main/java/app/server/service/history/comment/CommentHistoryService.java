package app.server.service.history.comment;

import app.server.model.history.comment.CommentHistory;
import app.server.service.history.HistoryService;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CommentHistoryService extends HistoryService<CommentHistory> {
    Mono<List<String>> getHistory(@NonNull String commentHexId, @NonNull String userHexId);
}