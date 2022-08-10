package app.server.storage.repository.report;

import app.server.model.report.Report;
import app.server.storage.repository.Repository;

public interface ReportRepository<T extends Report> extends Repository<T> {}