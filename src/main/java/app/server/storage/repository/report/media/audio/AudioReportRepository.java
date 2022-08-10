package app.server.storage.repository.report.media.audio;

import app.server.model.media.audio.Audio;
import app.server.model.report.media.audio.AudioReport;
import app.server.storage.repository.report.media.MediaReportRepository;

public interface AudioReportRepository extends MediaReportRepository<Audio, AudioReport> {}