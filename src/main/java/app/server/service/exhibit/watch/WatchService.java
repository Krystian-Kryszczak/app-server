package app.server.service.exhibit.watch;

import app.server.model.exhibit.watch.Watch;
import app.server.service.exhibit.ExhibitService;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.watch.WatchRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class WatchService extends ExhibitService<Watch> {
    @Inject
    static WatchRepository watchRepo;
    @Inject
    UserHistoryService historyService;
    @Override
    protected Mono<Void> historyUserVote(String userHexId, String watchHexId, boolean up) {
        return historyService.userVoteWatch(userHexId, watchHexId, up);
    }
    @Override
    protected Mono<Void> historyUserCancelVote(String userHexId, String watchHexId) {
        return historyService.userCancelVoteWatch(userHexId, watchHexId);
    }
    @Override
    protected ExhibitRepository<Watch> getRepo() {
        return watchRepo;
    }
}