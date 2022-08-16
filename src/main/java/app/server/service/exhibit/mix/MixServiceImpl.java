package app.server.service.exhibit.mix;

import app.server.service.exhibit.music.SongService;
import app.server.service.exhibit.page.PageService;
import app.server.service.exhibit.post.PostService;
import app.server.service.exhibit.reel.ReelService;
import app.server.service.exhibit.snap.SnapService;
import app.server.service.exhibit.story.StoryService;
import app.server.service.exhibit.watch.WatchService;
import jakarta.inject.Singleton;

@Singleton
public class MixServiceImpl implements MixService {
    final PostService postService;
    final PageService pageService;
    final ReelService reelService;
    final SnapService snapService;
    final SongService songService;
    final StoryService storyService;
    final WatchService watchService;
    public MixServiceImpl(PostService postService, PageService pageService, ReelService reelService,
    SnapService snapService, SongService songService, StoryService storyService, WatchService watchService) {
        this.postService = postService;
        this.pageService = pageService;
        this.reelService = reelService;
        this.snapService = snapService;
        this.songService = songService;
        this.storyService = storyService;
        this.watchService = watchService;
    }
    // ---------------------------------------------------------------------------------------------------- //
    // TODO
}