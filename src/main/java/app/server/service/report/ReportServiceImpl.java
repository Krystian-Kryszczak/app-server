package app.server.service.report;

import app.server.model.being.user.User;
import app.server.model.exhibit.ExhibitType;
import app.server.model.report.being.community.group.GroupReport;
import app.server.model.report.being.user.UserReport;
import app.server.model.report.being.community.page.PageReport;
import app.server.model.report.exhibit.post.PostReport;
import app.server.model.report.exhibit.reel.ReelReport;
import app.server.model.report.exhibit.snap.SnapReport;
import app.server.model.report.exhibit.song.SongReport;
import app.server.model.report.exhibit.story.StoryReport;
import app.server.model.report.exhibit.watch.WatchReport;
import app.server.model.report.media.audio.AudioReport;
import app.server.model.report.media.image.ImageReport;
import app.server.model.report.media.video.VideoReport;
import app.server.storage.repository.report.being.community.group.GroupReportRepository;
import app.server.storage.repository.report.being.user.UserReportRepository;
import app.server.storage.repository.report.being.community.page.PageReportRepository;
import app.server.storage.repository.report.comment.CommentReportRepository;
import app.server.storage.repository.report.exhibit.post.PostReportRepository;
import app.server.storage.repository.report.exhibit.reel.ReelReportRepository;
import app.server.storage.repository.report.exhibit.snap.SnapReportRepository;
import app.server.storage.repository.report.exhibit.song.SongReportRepository;
import app.server.storage.repository.report.exhibit.story.StoryReportRepository;
import app.server.storage.repository.report.exhibit.watch.WatchReportRepository;
import app.server.storage.repository.report.media.audio.AudioReportRepository;
import app.server.storage.repository.report.media.image.ImageReportRepository;
import app.server.storage.repository.report.media.video.VideoReportRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

@Singleton
public class ReportServiceImpl implements ReportService {
    // Being
    @Inject UserReportRepository userReportRepository;
    @Inject GroupReportRepository groupReportRepository;
    // Exhibit
    @Inject PageReportRepository pageReportRepository;
    @Inject PostReportRepository postReportRepository;
    @Inject ReelReportRepository reelReportRepository;
    @Inject SnapReportRepository snapReportRepository;
    @Inject SongReportRepository songReportRepository;
    @Inject StoryReportRepository storyReportRepository;
    @Inject WatchReportRepository watchReportRepository;
    // Comment
    @Inject CommentReportRepository commentReportRepository;
    // Media
    @Inject VideoReportRepository videoReportRepository;
    @Inject ImageReportRepository imageReportRepository;
    @Inject AudioReportRepository audioReportRepository;
    // ---------------------------------------------[ Being ]-------------------------------------------- //
    public Publisher<Boolean> reportUser(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(userReportRepository.save(new UserReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportGroup(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(groupReportRepository.save(new GroupReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    // ---------------------------------------------[ Exhibit ]-------------------------------------------- //
    public Publisher<Boolean> reportPage(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(pageReportRepository.save(new PageReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportPost(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(postReportRepository.save(new PostReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportWatch(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(watchReportRepository.save(new WatchReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportReel(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(reelReportRepository.save(new ReelReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportSnap(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(snapReportRepository.save(new SnapReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportStory(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(storyReportRepository.save(new StoryReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportSong(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(songReportRepository.save(new SongReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    // ---------------------------------------------[ Comment ]--------------------------------------------- //
    public Publisher<Boolean> reportComment(@NonNull ObjectId id, ExhibitType type, @NonNull User user, @NonNull String content) {
        return null;
    }
    // ---------------------------------------------[ Media ]--------------------------------------------- //
    public Publisher<Boolean> reportVideo(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(videoReportRepository.save(new VideoReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportImage(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(imageReportRepository.save(new ImageReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportAudio(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(audioReportRepository.save(new AudioReport(user, id, content, false))).thenReturn(true).onErrorReturn(false);
    }
    // --------------------------------------------------------------------------------------------------- //
    // ToAdmin //
    // ---------------------------------------------[ Being ]-------------------------------------------- //
    public Publisher<Boolean> reportUserToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(userReportRepository.save(new UserReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportGroupToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(groupReportRepository.save(new GroupReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    // ---------------------------------------------[ Exhibit ]-------------------------------------------- //
    public Publisher<Boolean> reportPageToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(pageReportRepository.save(new PageReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportPostToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(postReportRepository.save(new PostReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportWatchToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(watchReportRepository.save(new WatchReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportReelToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(reelReportRepository.save(new ReelReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportSnapToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(snapReportRepository.save(new SnapReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportStoryToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(storyReportRepository.save(new StoryReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportSongToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(songReportRepository.save(new SongReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    // ---------------------------------------------[ Comment ]--------------------------------------------- //
    public Publisher<Boolean> reportCommentToAdmin(@NonNull ObjectId id, ExhibitType type, @NonNull User user, @NonNull String content) {
        return null;
    }
    // ---------------------------------------------[ Media ]--------------------------------------------- //
    public Publisher<Boolean> reportVideoToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(videoReportRepository.save(new VideoReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportImageToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(imageReportRepository.save(new ImageReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
    public Publisher<Boolean> reportAudioToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content) {
        return Mono.from(audioReportRepository.save(new AudioReport(user, id, content, true))).thenReturn(true).onErrorReturn(false);
    }
}