package app.server.service.report;

import app.server.model.being.user.User;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import javax.validation.constraints.NotBlank;

@Singleton
public class ReportService { // TODO All
    // ---------------------------------------------[ Being ]-------------------------------------------- //
    public Publisher<Boolean> reportUser(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportGroup(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    // ---------------------------------------------[ Exhibit ]-------------------------------------------- //
    public Publisher<Boolean> reportPost(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportWatch(String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportReel(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportSnap(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportStory(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportSong(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    // ---------------------------------------------[ Media ]--------------------------------------------- //
    public Publisher<Boolean> reportVideo(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) {
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportImage(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) {
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportAudio(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) {
        return null; // TODO «·»
    }
    // --------------------------------------------------------------------------------------------------- //
    // ToAdmin //
    // ---------------------------------------------[ Being ]-------------------------------------------- //
    public Publisher<Boolean> reportUserToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportGroupToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    // ---------------------------------------------[ Exhibit ]-------------------------------------------- //
    public Publisher<Boolean> reportPostToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportWatchToAdmin(String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportReelToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportSnapToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportStoryToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportSongToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) { // TODO
        return null; // TODO «·»
    }
    // ---------------------------------------------[ Media ]--------------------------------------------- //
    public Publisher<Boolean> reportVideoToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) {
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportImageToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) {
        return null; // TODO «·»
    }
    public Publisher<Boolean> reportAudioToAdmin(@NonNull String hexId, @NonNull User user, @NonNull @NotBlank String content) {
        return null; // TODO «·»
    }
}