package app.server.service.history.being;

import app.server.model.being.Being;
import app.server.model.history.being.BeingHistory;
import app.server.service.history.HistoryServiceImpl;

public abstract class BeingHistoryServiceImpl<T extends Being, S extends BeingHistory<T>> extends HistoryServiceImpl<S> {}