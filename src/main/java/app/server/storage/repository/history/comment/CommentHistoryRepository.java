package app.server.storage.repository.history.comment;

import app.server.model.comment.Comment;
import app.server.model.history.comment.CommentHistory;
import app.server.storage.repository.history.HistoryRepository;

public interface CommentHistoryRepository extends HistoryRepository<Comment, CommentHistory> {}