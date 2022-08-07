package app.server.service.exhibit.story;

import app.server.model.exhibit.story.Story;
import app.server.service.exhibit.ExhibitServiceImpl;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.story.StoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class StoryServiceImpl extends ExhibitServiceImpl<Story> implements StoryService {
    @Inject
    static StoryRepository storyRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    public Mono<Void> historyUserVote(String userHexId, String storyHexId, boolean up) {
        return historyService.userVoteStory(userHexId, storyHexId, up);
    }
    @Override
    public Mono<Void> historyUserCancelVote(String userHexId, String storyHexId) {
        return historyService.userCancelVoteStory(userHexId, storyHexId);
    }
    @Override
    protected ExhibitRepository<Story> getRepo() {
        return storyRepo;
    }
}