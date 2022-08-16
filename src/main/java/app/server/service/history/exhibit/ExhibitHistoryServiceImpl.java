package app.server.service.history.exhibit;

import app.server.storage.repository.history.exhibit.page.PageHistoryRepository;
import app.server.storage.repository.history.exhibit.post.PostHistoryRepository;
import app.server.storage.repository.history.exhibit.reel.ReelHistoryRepository;
import app.server.storage.repository.history.exhibit.snap.SnapHistoryRepository;
import app.server.storage.repository.history.exhibit.song.SongHistoryRepository;
import app.server.storage.repository.history.exhibit.story.StoryHistoryRepository;
import app.server.storage.repository.history.exhibit.watch.WatchHistoryRepository;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;

@Singleton
public class ExhibitHistoryServiceImpl implements ExhibitHistoryService { // TODO
    final PageHistoryRepository pageHistoryRepository; final PostHistoryRepository postHistoryRepository; final ReelHistoryRepository reelHistoryRepository;
    final SnapHistoryRepository snapHistoryRepository; final SongHistoryRepository songHistoryRepository; final StoryHistoryRepository storyHistoryRepository;
    final WatchHistoryRepository watchHistoryRepository;
    public ExhibitHistoryServiceImpl(PageHistoryRepository pageHistoryRepository, PostHistoryRepository postHistoryRepository,
    ReelHistoryRepository reelHistoryRepository, SnapHistoryRepository snapHistoryRepository, SongHistoryRepository songHistoryRepository,
    StoryHistoryRepository storyHistoryRepository, WatchHistoryRepository watchHistoryRepository) {
        this.pageHistoryRepository = pageHistoryRepository;
        this.postHistoryRepository = postHistoryRepository;
        this.reelHistoryRepository = reelHistoryRepository;
        this.snapHistoryRepository = snapHistoryRepository;
        this.songHistoryRepository = songHistoryRepository;
        this.storyHistoryRepository = storyHistoryRepository;
        this.watchHistoryRepository = watchHistoryRepository;
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public Flux<String> getPageHistory(String pageHexId, String userHexId) {
        return pageHistoryRepository.getHistory(pageHexId, 15).map(a -> a.toString());
    }
    @Override
    public Flux<String> getPostHistory(String postHexId, String userHexId) {
        return null;
    }
    @Override
    public Flux<String> getReelHistory(String reelHexId, String userHexId) {
        return null;
    }
    @Override
    public Flux<String> getSnapHistory(String snapHexId, String userHexId) {
        return null;
    }
    @Override
    public Flux<String> getSongHistory(String songHexId, String userHexId) {
        return null;
    }
    @Override
    public Flux<String> getStoryHistory(String storyHexId, String userHexId) {
        return null;
    }
    @Override
    public Flux<String> getWatchHistory(String watchHexId, String userHexId) {
        return null;
    }
}