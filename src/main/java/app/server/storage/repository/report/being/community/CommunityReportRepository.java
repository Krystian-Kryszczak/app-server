package app.server.storage.repository.report.being.community;

import app.server.model.being.community.Community;
import app.server.model.report.being.community.CommunityReport;
import app.server.storage.repository.report.being.BeingReportRepository;

public interface CommunityReportRepository<T extends Community, S extends CommunityReport<T>> extends BeingReportRepository<T, S> {}