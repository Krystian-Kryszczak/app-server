package app.server.service.exhibit.reel;

import app.server.model.exhibit.reel.Reel;
import app.server.service.exhibit.ExhibitServiceImpl;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.reel.ReelRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class ReelServiceImpl extends ExhibitServiceImpl<Reel> implements ReelService {
    @Inject
    static ReelRepository reelRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    public Mono<Void> historyUserVote(String userHexId, String reelHexId, boolean up) {
        return historyService.userVoteReel(userHexId, reelHexId, up);
    }
    @Override
    public Mono<Void> historyUserCancelVote(String userHexId, String reelHexId) {
        return historyService.userCancelVoteReel(userHexId, reelHexId);
    }
    @Override
    protected ExhibitRepository<Reel> getRepo() {
        return reelRepo;
    }
}