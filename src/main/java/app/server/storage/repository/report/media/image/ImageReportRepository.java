package app.server.storage.repository.report.media.image;

import app.server.model.media.image.Image;
import app.server.model.report.media.image.ImageReport;
import app.server.storage.repository.report.media.MediaReportRepository;

public interface ImageReportRepository extends MediaReportRepository<Image, ImageReport> {}