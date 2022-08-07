package app.server.service.exhibit.snap;

import app.server.model.exhibit.snap.Snap;
import app.server.service.exhibit.ExhibitServiceImpl;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.snap.SnapRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class SnapServiceImpl extends ExhibitServiceImpl<Snap> implements SnapService {
    @Inject
    static SnapRepository snapRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    public Mono<Void> historyUserVote(String userHexId, String snapHexId, boolean up) {
        return historyService.userVoteSnap(userHexId, snapHexId, up);
    }
    @Override
    public Mono<Void> historyUserCancelVote(String userHexId, String snapHexId) {
        return historyService.userCancelVoteSnap(userHexId, snapHexId);
    }
    @Override
    protected ExhibitRepository<Snap> getRepo() {
        return snapRepo;
    }
}