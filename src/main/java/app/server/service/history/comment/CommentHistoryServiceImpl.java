package app.server.service.history.comment;

import app.server.model.history.comment.CommentHistory;
import app.server.service.history.HistoryServiceImpl;
import app.server.storage.repository.history.comment.CommentHistoryRepository;
import com.google.gson.Gson;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CommentHistoryServiceImpl extends HistoryServiceImpl<CommentHistory> implements CommentHistoryService {
    final CommentHistoryRepository commentHistoryRepository; final Gson gson = new Gson();
    public CommentHistoryServiceImpl(CommentHistoryRepository commentHistoryRepository) {
        this.commentHistoryRepository = commentHistoryRepository;
    }
    public Mono<List<String>> getHistory(@NonNull String commentHexId, @NonNull String userHexId) {
        return Flux.from(commentHistoryRepository.getHistory(commentHexId, 12)).map(gson::toJson).collect(Collectors.toList());
    }
}