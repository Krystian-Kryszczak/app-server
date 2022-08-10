package app.server.storage.repository.report.being;

import app.server.model.being.Being;
import app.server.model.report.being.BeingReport;
import app.server.storage.repository.report.ReportRepository;

public interface BeingReportRepository<T extends Being, S extends BeingReport<T>> extends ReportRepository<S> {}