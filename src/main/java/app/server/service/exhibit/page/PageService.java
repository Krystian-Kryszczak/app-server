package app.server.service.exhibit.page;

import app.server.model.exhibit.page.Page;
import app.server.service.exhibit.ExhibitService;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.page.PageRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class PageService extends ExhibitService<Page> {
    @Inject
    static PageRepository pageRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    protected Mono<Void> historyUserVote(String userHexId, String pageHexId, boolean up) {
        return historyService.userVotePage(userHexId, pageHexId, up);
    }
    @Override
    protected Mono<Void> historyUserCancelVote(String userHexId, String pageHexId) {
        return historyService.userCancelVotePage(userHexId, pageHexId);
    }
    @Override
    protected ExhibitRepository<Page> getRepo() {
        return pageRepo;
    }
}