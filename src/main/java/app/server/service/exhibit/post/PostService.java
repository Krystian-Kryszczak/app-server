package app.server.service.exhibit.post;

import app.server.model.exhibit.post.Post;
import app.server.service.exhibit.ExhibitService;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.post.PostRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class PostService extends ExhibitService<Post> {
    @Inject
    static PostRepository postRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    protected Mono<Void> historyUserVote(String userHexId, String postHexId, boolean up) {
        return historyService.userVotePost(userHexId, postHexId, up);
    }
    @Override
    protected Mono<Void> historyUserCancelVote(String userHexId, String postHexId) {
        return historyService.userCancelVotePost(userHexId, postHexId);
    }
    @Override
    protected ExhibitRepository<Post> getRepo() {
        return postRepo;
    }
}