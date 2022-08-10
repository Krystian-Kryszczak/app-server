package app.server.storage.repository.report.exhibit.song;

import app.server.model.exhibit.song.Song;
import app.server.model.report.exhibit.song.SongReport;
import app.server.storage.repository.report.exhibit.ExhibitReportRepository;

public interface SongReportRepository extends ExhibitReportRepository<Song, SongReport> {}