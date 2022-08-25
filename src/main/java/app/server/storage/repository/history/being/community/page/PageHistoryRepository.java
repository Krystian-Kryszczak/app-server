package app.server.storage.repository.history.being.community.page;

import app.server.model.being.community.page.Page;
import app.server.model.history.being.community.page.PageHistory;
import app.server.storage.repository.history.being.community.CommunityHistoryRepository;

public interface PageHistoryRepository extends CommunityHistoryRepository<Page, PageHistory> {}