package app.server.service.history.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.model.history.being.community.group.GroupHistory;
import app.server.service.history.being.community.CommunityHistoryService;

public interface GroupHistoryService extends CommunityHistoryService<Group, GroupHistory> {}