package app.server.storage.repository.history.being.community;

import app.server.model.being.community.Community;
import app.server.model.history.being.community.CommunityHistory;
import app.server.storage.repository.history.being.BeingHistoryRepository;

public interface CommunityHistoryRepository<T extends Community, S extends CommunityHistory<T>> extends BeingHistoryRepository<T, S> {}