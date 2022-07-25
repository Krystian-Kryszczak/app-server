package app.server.service.exhibit.story;

import app.server.model.exhibit.story.Story;
import app.server.service.exhibit.ExhibitService;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.story.StoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class StoryService extends ExhibitService<Story> {
    @Inject
    static StoryRepository storyRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    protected Mono<Void> historyUserVote(String userHexId, String storyHexId, boolean up) {
        return historyService.userVoteStory(userHexId, storyHexId, up);
    }
    @Override
    protected Mono<Void> historyUserCancelVote(String userHexId, String storyHexId) {
        return historyService.userCancelVoteStory(userHexId, storyHexId);
    }
    @Override
    protected ExhibitRepository<Story> getRepo() {
        return storyRepo;
    }
}