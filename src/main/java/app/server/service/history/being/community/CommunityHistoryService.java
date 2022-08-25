package app.server.service.history.being.community;

import app.server.model.being.Being;
import app.server.model.history.being.BeingHistory;
import app.server.service.history.HistoryService;

public interface CommunityHistoryService<T extends Being, S extends BeingHistory<T>> extends HistoryService<S> {}