package app.server.storage.repository.history.exhibit.post;

import app.server.model.exhibit.post.Post;
import app.server.model.history.exhibit.post.PostHistory;
import app.server.storage.repository.history.exhibit.ExhibitHistoryRepository;

public interface PostHistoryRepository extends ExhibitHistoryRepository<Post, PostHistory> {}