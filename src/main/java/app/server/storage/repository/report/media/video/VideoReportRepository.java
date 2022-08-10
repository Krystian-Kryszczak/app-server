package app.server.storage.repository.report.media.video;

import app.server.model.media.video.Video;
import app.server.model.report.media.video.VideoReport;
import app.server.storage.repository.report.media.MediaReportRepository;

public interface VideoReportRepository extends MediaReportRepository<Video, VideoReport> {}