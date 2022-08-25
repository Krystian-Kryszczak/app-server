package app.server.service.history.being.community.page;

import app.server.model.being.community.page.Page;
import app.server.model.history.being.community.page.PageHistory;
import app.server.service.history.being.community.CommunityHistoryService;
import reactor.core.publisher.Flux;

public interface PageHistoryService extends CommunityHistoryService<Page, PageHistory> {
    Flux<String> getPageHistory(String pageHexId, String userHexId);
}