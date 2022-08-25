package app.server.storage.repository.history.being.user;

import app.server.model.being.user.User;
import app.server.model.history.being.user.UserHistory;
import app.server.storage.repository.history.being.BeingHistoryRepository;

public interface UserHistoryRepository extends BeingHistoryRepository<User, UserHistory> {}