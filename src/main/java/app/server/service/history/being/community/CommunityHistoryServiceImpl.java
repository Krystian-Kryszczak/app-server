package app.server.service.history.being.community;

import app.server.model.being.community.Community;
import app.server.model.history.being.community.CommunityHistory;

public abstract class CommunityHistoryServiceImpl<T extends Community, S extends CommunityHistory<T>> implements CommunityHistoryService<T, S> {}