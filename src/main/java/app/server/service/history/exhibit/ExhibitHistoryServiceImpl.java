package app.server.service.history.exhibit;

import app.server.storage.repository.history.exhibit.post.PostHistoryRepository;
import app.server.storage.repository.history.exhibit.reel.ReelHistoryRepository;
import app.server.storage.repository.history.exhibit.snap.SnapHistoryRepository;
import app.server.storage.repository.history.exhibit.song.SongHistoryRepository;
import app.server.storage.repository.history.exhibit.story.StoryHistoryRepository;
import app.server.storage.repository.history.exhibit.watch.WatchHistoryRepository;
import com.google.gson.Gson;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
public class ExhibitHistoryServiceImpl implements ExhibitHistoryService {
    final PostHistoryRepository postHistoryRepository; final ReelHistoryRepository reelHistoryRepository;
    final SnapHistoryRepository snapHistoryRepository; final SongHistoryRepository songHistoryRepository; final StoryHistoryRepository storyHistoryRepository;
    final WatchHistoryRepository watchHistoryRepository;
    final Gson gson = new Gson();
    public ExhibitHistoryServiceImpl(PostHistoryRepository postHistoryRepository,
    ReelHistoryRepository reelHistoryRepository, SnapHistoryRepository snapHistoryRepository, SongHistoryRepository songHistoryRepository,
    StoryHistoryRepository storyHistoryRepository, WatchHistoryRepository watchHistoryRepository) {
        this.postHistoryRepository = postHistoryRepository;
        this.reelHistoryRepository = reelHistoryRepository;
        this.snapHistoryRepository = snapHistoryRepository;
        this.songHistoryRepository = songHistoryRepository;
        this.storyHistoryRepository = storyHistoryRepository;
        this.watchHistoryRepository = watchHistoryRepository;
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public Flux<String> getPostHistory(String postHexId, String userHexId) {
        return userCanGetHistory(userHexId).flux().flatMap(can -> can ? postHistoryRepository.getHistory(postHexId, 12).map(gson::toJson) : Flux.empty());
    }
    @Override
    public Flux<String> getReelHistory(String reelHexId, String userHexId) {
        return userCanGetHistory(userHexId).flux().flatMap(can -> can ? reelHistoryRepository.getHistory(reelHexId, 12).map(gson::toJson) : Flux.empty());
    }
    @Override
    public Flux<String> getSnapHistory(String snapHexId, String userHexId) {
        return userCanGetHistory(userHexId).flux().flatMap(can -> can ? snapHistoryRepository.getHistory(snapHexId, 12).map(gson::toJson) : Flux.empty());
    }
    @Override
    public Flux<String> getSongHistory(String songHexId, String userHexId) {
        return userCanGetHistory(userHexId).flux().flatMap(can -> can ? songHistoryRepository.getHistory(songHexId, 12).map(gson::toJson) : Flux.empty());
    }
    @Override
    public Flux<String> getStoryHistory(String storyHexId, String userHexId) {
        return userCanGetHistory(userHexId).flux().flatMap(can -> can ? storyHistoryRepository.getHistory(storyHexId, 12).map(gson::toJson) : Flux.empty());
    }
    @Override
    public Flux<String> getWatchHistory(String watchHexId, String userHexId) {
        return userCanGetHistory(userHexId).flux().flatMap(can -> can ? watchHistoryRepository.getHistory(watchHexId, 12).map(gson::toJson) : Flux.empty());
    }
    private Mono<Boolean> userCanGetHistory(String userHexId) {
        return Mono.just(ObjectId.isValid(userHexId));
    }
}