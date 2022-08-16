package app.server.service.history.being.group;

import app.server.model.history.being.group.GroupHistory;
import app.server.service.history.HistoryServiceImpl;
import jakarta.inject.Singleton;

@Singleton
public class GroupHistoryServiceImpl extends HistoryServiceImpl<GroupHistory> implements GroupHistoryService {}