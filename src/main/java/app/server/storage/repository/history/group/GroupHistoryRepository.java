package app.server.storage.repository.history.group;

import app.server.model.being.group.Group;
import app.server.model.history.group.GroupHistory;
import app.server.storage.repository.history.HistoryRepository;

public interface GroupHistoryRepository extends HistoryRepository<GroupHistory, Group> {}