package app.server.storage.repository.history.exhibit.watch;

import app.server.model.exhibit.watch.Watch;
import app.server.model.history.exhibit.watch.WatchHistory;
import app.server.storage.repository.history.exhibit.ExhibitHistoryRepository;

public interface WatchHistoryRepository extends ExhibitHistoryRepository<Watch, WatchHistory> {}