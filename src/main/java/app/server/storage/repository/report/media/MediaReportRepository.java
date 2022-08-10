package app.server.storage.repository.report.media;

import app.server.model.media.Media;
import app.server.model.report.media.MediaReport;
import app.server.storage.repository.report.ReportRepository;

public interface MediaReportRepository<T extends Media<T>, S extends MediaReport<T>> extends ReportRepository<S> {}