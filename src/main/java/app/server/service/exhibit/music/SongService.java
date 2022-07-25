package app.server.service.exhibit.music;

import app.server.model.exhibit.song.Song;
import app.server.service.exhibit.ExhibitService;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.music.song.SongRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class SongService extends ExhibitService<Song> {
    @Inject
    static SongRepository songRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    protected Mono<Void> historyUserVote(String userHexId, String songHexId, boolean up) {
        return historyService.userVoteSong(userHexId, songHexId, up);
    }
    @Override
    protected Mono<Void> historyUserCancelVote(String userHexId, String songHexId) {
        return historyService.userCancelVoteSong(userHexId, songHexId);
    }
    @Override
    protected ExhibitRepository<Song> getRepo() {
        return songRepo;
    }
}