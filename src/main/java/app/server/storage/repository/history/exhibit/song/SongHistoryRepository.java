package app.server.storage.repository.history.exhibit.song;

import app.server.model.exhibit.song.Song;
import app.server.model.history.exhibit.song.SongHistory;
import app.server.storage.repository.history.exhibit.ExhibitHistoryRepository;

public interface SongHistoryRepository extends ExhibitHistoryRepository<Song, SongHistory> {}