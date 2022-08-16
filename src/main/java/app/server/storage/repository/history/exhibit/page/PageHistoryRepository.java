package app.server.storage.repository.history.exhibit.page;

import app.server.model.exhibit.page.Page;
import app.server.model.history.exhibit.page.PageHistory;
import app.server.storage.repository.history.exhibit.ExhibitHistoryRepository;

public interface PageHistoryRepository extends ExhibitHistoryRepository<Page, PageHistory> {}