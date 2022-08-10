package app.server.service.exhibit.music;

import app.server.model.exhibit.song.Song;
import app.server.service.exhibit.ExhibitServiceImpl;
import app.server.service.history.being.user.UserHistoryService;
import app.server.storage.repository.exhibit.ExhibitRepository;
import app.server.storage.repository.exhibit.song.SongRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class SongServiceImpl extends ExhibitServiceImpl<Song> implements SongService {
    @Inject
    static SongRepository songRepo;
    @Inject
    static UserHistoryService historyService;
    @Override
    public Mono<Void> historyUserVote(String userHexId, String songHexId, boolean up) {
        return historyService.userVoteSong(userHexId, songHexId, up);
    }
    @Override
    public Mono<Void> historyUserCancelVote(String userHexId, String songHexId) {
        return historyService.userCancelVoteSong(userHexId, songHexId);
    }
    @Override
    public ExhibitRepository<Song> getRepo() {
        return songRepo;
    }
}
