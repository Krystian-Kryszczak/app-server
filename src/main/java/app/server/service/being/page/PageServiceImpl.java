package app.server.service.being.page;

import app.server.model.exhibit.page.Page;
import app.server.service.exhibit.ExhibitServiceImpl;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.page.PageRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class PageServiceImpl extends ExhibitServiceImpl<Page> implements PageService {
    @Inject
    static PageRepository pageRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    public Mono<Void> historyUserVote(String userHexId, String pageHexId, boolean up) {
        return historyService.userVotePage(userHexId, pageHexId, up);
    }
    @Override
    public Mono<Void> historyUserCancelVote(String userHexId, String pageHexId) {
        return historyService.userCancelVotePage(userHexId, pageHexId);
    }
    @Override
    protected ExhibitRepository<Page> getRepo() {
        return pageRepo;
    }
}