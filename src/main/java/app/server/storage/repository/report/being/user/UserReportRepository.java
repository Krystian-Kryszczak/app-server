package app.server.storage.repository.report.being.user;

import app.server.model.being.user.User;
import app.server.model.report.being.user.UserReport;
import app.server.storage.repository.report.being.BeingReportRepository;

public interface UserReportRepository extends BeingReportRepository<User, UserReport> {}