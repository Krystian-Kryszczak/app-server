package app.server.service.news;

import app.server.model.notification.Notification;
import app.server.service.being.community.page.PageService;
import app.server.service.exhibit.music.SongService;
import app.server.service.exhibit.post.PostService;
import app.server.service.exhibit.reel.ReelService;
import app.server.service.exhibit.snap.SnapService;
import app.server.service.exhibit.story.StoryService;
import app.server.service.exhibit.watch.WatchService;
import app.server.storage.repository.notification.NotificationRepository;
import com.google.gson.Gson;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class NewsServiceImpl implements NewsService {
    final NotificationRepository notificationRepo;
    final PostService postService;
    final PageService pageService;
    final ReelService reelService;
    final SnapService snapService;
    final SongService songService;
    final StoryService storyService;
    final WatchService watchService;
    final Gson gson = new Gson();
    public NewsServiceImpl(NotificationRepository notificationRepo, PostService postService, PageService pageService, ReelService reelService, SnapService snapService, SongService songService, StoryService storyService, WatchService watchService) {
        this.notificationRepo = notificationRepo;
        this.postService = postService;
        this.pageService = pageService;
        this.reelService = reelService;
        this.snapService = snapService;
        this.songService = songService;
        this.storyService = storyService;
        this.watchService = watchService;
    }
    @Override
    public Mono<Map<String, String>> getNews(String targetHexId) {
        Map<String, String> news = new LinkedHashMap<>();
        Mono<List<Document>> notifications = notificationRepo.findDocByTargetId(targetHexId).collect(Collectors.toList());
        return notifications.map(list -> list.stream().map(Document::toJson).collect(Collectors.toList()))
            .mapNotNull(list -> news.put("notifications", gson.toJson(list))).thenReturn(news);
    }
    @Override
    public Mono<Boolean> sendNotificationForUser(String userHexId, String content) {
        if (!ObjectId.isValid(userHexId)) return Mono.just(false);
        return Mono.from(notificationRepo.save(new Notification(Notification.Target.user, userHexId, content))).thenReturn(true).onErrorReturn(false);
    }
    @Override
    public Mono<Boolean> sendNotificationForGroup(String groupHexId, String content) {
        if (!ObjectId.isValid(groupHexId)) return Mono.just(false);
        return Mono.from(notificationRepo.save(new Notification(Notification.Target.group, groupHexId, content))).thenReturn(true).onErrorReturn(false);
    }
}