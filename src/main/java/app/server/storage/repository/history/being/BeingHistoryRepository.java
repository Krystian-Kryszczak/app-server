package app.server.storage.repository.history.being;

import app.server.model.being.Being;
import app.server.model.history.History;
import app.server.storage.repository.history.HistoryRepository;

public interface BeingHistoryRepository<T extends Being, S extends History> extends HistoryRepository<T, S> {}