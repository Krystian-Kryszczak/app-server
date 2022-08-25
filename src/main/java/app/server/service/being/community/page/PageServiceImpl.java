package app.server.service.being.community.page;

import app.server.model.being.community.page.Page;
import app.server.service.being.community.CommunityServiceImpl;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.being.community.page.PageRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class PageServiceImpl extends CommunityServiceImpl<Page> implements PageService {
    @Inject
    static PageRepository pageRepo;
    @Inject
    static UserHistoryService historyService;
    protected PageRepository getRepo() {
        return pageRepo;
    }
//    public Mono<Void> historyUserVote(String userHexId, String pageHexId, boolean up) {
//        return historyService.userVotePage(userHexId, pageHexId, up);
//    }
//    public Mono<Void> historyUserCancelVote(String userHexId, String pageHexId) {
//        return historyService.userCancelVotePage(userHexId, pageHexId);
//    }
}