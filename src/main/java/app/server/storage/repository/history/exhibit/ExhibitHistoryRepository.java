package app.server.storage.repository.history.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.model.history.exhibit.ExhibitHistory;
import app.server.storage.repository.history.HistoryRepository;

public interface ExhibitHistoryRepository<T extends Exhibit<T>, S extends ExhibitHistory<T>> extends HistoryRepository<T, S> {}