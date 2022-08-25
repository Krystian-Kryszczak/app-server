package app.server.model.exhibit;

import app.server.model.exhibit.post.Post;
import app.server.model.exhibit.reel.Reel;
import app.server.model.exhibit.snap.Snap;
import app.server.model.exhibit.song.Song;
import app.server.model.exhibit.story.Story;
import app.server.model.exhibit.watch.Watch;
import app.server.service.exhibit.ExhibitService;
import app.server.service.exhibit.music.SongService;
import app.server.service.exhibit.post.PostService;
import app.server.service.exhibit.reel.ReelService;
import app.server.service.exhibit.snap.SnapService;
import app.server.service.exhibit.story.StoryService;
import app.server.service.exhibit.watch.WatchService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import lombok.Getter;

@Getter
public enum ExhibitType {
    post(Post.class, "posts"),
    reel(Reel.class, "reels"),
    snap(Snap.class, "snaps"),
    song(Song.class, "songs"),
    story(Story.class, "stories"),
    watch(Watch.class, "watch");
    @NonNull private final Class<?> clazz; @NonNull private final String urlModifier;
    ExhibitType(@NonNull Class<?> clazz, @NonNull String urlModifier) {
        this.clazz = clazz;
        this.urlModifier = urlModifier;
    }
    public static ExhibitType getTypeByClass(Class<?> clazz) {
        for (ExhibitType type : ExhibitType.values()) {
            if (type.getClazz().getSimpleName().equals(clazz.getSimpleName())) {
                return type;
            }
        }
        return null;
    }
    public static ExhibitType getTypeByUrlModifier(String urlModifier) {
        for (ExhibitType type : ExhibitType.values()) {
            if (type.getUrlModifier().equals(urlModifier)) {
                return type;
            }
        }
        return null;
    }
    public ExhibitService<?> getExhibitService() {
        return Service.getService(this);
    }
    protected static class Service {
        static PostService postService;
        static ReelService reelService;
        static SnapService snapService;
        static SongService songService;
        static StoryService storyService;
        static WatchService watchService;
        @Inject
        public Service(PostService postService, ReelService reelService, SnapService snapService, SongService songService, StoryService storyService, WatchService watchService) {
            Service.postService = postService;
            Service.reelService = reelService;
            Service.snapService = snapService;
            Service.songService = songService;
            Service.storyService = storyService;
            Service.watchService = watchService;
        }
        protected static ExhibitService<?> getService(ExhibitType type) {
            ExhibitService<?> service = null;
            switch (type) {
                case post:
                    service = postService;
                    break;
                case reel:
                    service = reelService;
                    break;
                case snap:
                    service = snapService;
                    break;
                case song:
                    service = songService;
                    break;
                case story:
                    service = storyService;
                    break;
                case watch:
                    service = watchService;
                    break;
            }
            return service;
        }
    }
}