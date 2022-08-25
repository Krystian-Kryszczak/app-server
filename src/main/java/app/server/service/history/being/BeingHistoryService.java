package app.server.service.history.being;

import app.server.model.being.Being;
import app.server.model.history.being.BeingHistory;
import app.server.service.history.HistoryService;

public interface BeingHistoryService<T extends Being, S extends BeingHistory<T>> extends HistoryService<S> {}