package app.server.storage.repository.report.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.model.report.exhibit.ExhibitReport;
import app.server.storage.repository.report.ReportRepository;

public interface ExhibitReportRepository<T extends Exhibit<T>, S extends ExhibitReport<T>> extends ReportRepository<S> {}