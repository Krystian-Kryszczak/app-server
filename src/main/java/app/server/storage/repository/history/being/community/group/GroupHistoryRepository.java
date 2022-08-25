package app.server.storage.repository.history.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.model.history.being.community.group.GroupHistory;
import app.server.storage.repository.history.being.community.CommunityHistoryRepository;

public interface GroupHistoryRepository extends CommunityHistoryRepository<Group, GroupHistory> {}