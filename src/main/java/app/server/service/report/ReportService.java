package app.server.service.report;

import app.server.model.being.user.User;
import io.micronaut.core.annotation.NonNull;
import org.reactivestreams.Publisher;

import javax.validation.constraints.NotBlank;

public interface ReportService {
    // ---------------------------------------------[ Being ]-------------------------------------------- //
    Publisher<Boolean> reportUser(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportGroup(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Exhibit ]-------------------------------------------- //
    Publisher<Boolean> reportPost(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportWatch(String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportReel(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportSnap(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportStory(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportSong(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Media ]--------------------------------------------- //
    Publisher<Boolean> reportVideo(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportImage(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportAudio(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    // --------------------------------------------------------------------------------------------------- //
    // ToAdmin //
    // ---------------------------------------------[ Being ]-------------------------------------------- //
    Publisher<Boolean> reportUserToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportGroupToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Exhibit ]-------------------------------------------- //
    Publisher<Boolean> reportPostToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportWatchToAdmin(String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportReelToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportSnapToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportStoryToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportSongToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Media ]--------------------------------------------- //
    Publisher<Boolean> reportVideoToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportImageToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportAudioToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content);
}