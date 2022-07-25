package app.server.service.exhibit.snap;

import app.server.model.exhibit.snap.Snap;
import app.server.service.exhibit.ExhibitService;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.snap.SnapRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class SnapService extends ExhibitService<Snap> {
    @Inject
    static SnapRepository snapRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    protected Mono<Void> historyUserVote(String userHexId, String snapHexId, boolean up) {
        return historyService.userVoteSnap(userHexId, snapHexId, up);
    }
    @Override
    protected Mono<Void> historyUserCancelVote(String userHexId, String snapHexId) {
        return historyService.userCancelVoteSnap(userHexId, snapHexId);
    }
    @Override
    protected ExhibitRepository<Snap> getRepo() {
        return snapRepo;
    }
}