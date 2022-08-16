package app.server.storage.repository.history.exhibit.snap;

import app.server.model.exhibit.snap.Snap;
import app.server.model.history.exhibit.snap.SnapHistory;
import app.server.storage.repository.history.exhibit.ExhibitHistoryRepository;

public interface SnapHistoryRepository extends ExhibitHistoryRepository<Snap, SnapHistory> {}