package app.server.service.exhibit.post;

import app.server.model.exhibit.post.Post;
import app.server.service.exhibit.ExhibitService;
import app.server.service.exhibit.ExhibitServiceImpl;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.post.PostRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

public interface PostService extends ExhibitService<Post> {}