package app.server.service.history.being.community.page;

import app.server.model.being.community.page.Page;
import app.server.model.history.being.community.page.PageHistory;
import app.server.service.history.being.community.CommunityHistoryServiceImpl;
import app.server.storage.repository.history.being.community.page.PageHistoryRepository;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;

@Singleton
public class PageHistoryServiceImpl extends CommunityHistoryServiceImpl<Page, PageHistory> implements PageHistoryService {
    final PageHistoryRepository pageHistoryRepository;
    public PageHistoryServiceImpl(PageHistoryRepository pageHistoryRepository) {
        this.pageHistoryRepository = pageHistoryRepository;
    }
    @Override
    public Flux<String> getPageHistory(String pageHexId, String userHexId) {
        return pageHistoryRepository.getHistory(pageHexId, 12).map(Object::toString);
    }
}