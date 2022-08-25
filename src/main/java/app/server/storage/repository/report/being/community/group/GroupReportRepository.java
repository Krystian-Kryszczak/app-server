package app.server.storage.repository.report.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.model.report.being.community.group.GroupReport;
import app.server.storage.repository.report.being.community.CommunityReportRepository;

public interface GroupReportRepository extends CommunityReportRepository<Group, GroupReport> {}