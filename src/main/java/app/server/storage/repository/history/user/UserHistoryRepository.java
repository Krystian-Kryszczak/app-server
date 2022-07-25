package app.server.storage.repository.history.user;

import app.server.model.being.user.User;
import app.server.model.history.user.UserHistory;
import app.server.storage.repository.history.HistoryRepository;

public interface UserHistoryRepository extends HistoryRepository<UserHistory, User> {}