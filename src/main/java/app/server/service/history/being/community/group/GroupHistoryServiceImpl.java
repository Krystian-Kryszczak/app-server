package app.server.service.history.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.model.history.being.community.group.GroupHistory;
import app.server.service.history.being.community.CommunityHistoryServiceImpl;
import jakarta.inject.Singleton;

@Singleton
public class GroupHistoryServiceImpl extends CommunityHistoryServiceImpl<Group, GroupHistory> implements GroupHistoryService {}