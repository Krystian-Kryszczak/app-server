package app.server.service.report;

import app.server.model.being.user.User;
import app.server.model.exhibit.ExhibitType;
import io.micronaut.core.annotation.NonNull;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import javax.validation.constraints.NotBlank;

public interface ReportService {
    // ---------------------------------------------[ Being ]-------------------------------------------- //
    Publisher<Boolean> reportUser(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportGroup(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportPage(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Exhibit ]-------------------------------------------- //
    Publisher<Boolean> reportPost(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportWatch(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportReel(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportSnap(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportStory(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportSong(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Comments ]--------------------------------------------- //
    Publisher<Boolean> reportComment(@NonNull ObjectId id, ExhibitType type, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Media ]--------------------------------------------- //
    Publisher<Boolean> reportVideo(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportImage(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportAudio(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    // --------------------------------------------------------------------------------------------------- //
    // ToAdmin //
    // ---------------------------------------------[ Being ]-------------------------------------------- //
    Publisher<Boolean> reportUserToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportGroupToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportPageToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Exhibit ]-------------------------------------------- //
    Publisher<Boolean> reportPostToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportWatchToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportReelToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportSnapToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportStoryToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportSongToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Comments ]--------------------------------------------- //
    Publisher<Boolean> reportCommentToAdmin(@NonNull ObjectId id, ExhibitType type, @NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------[ Media ]--------------------------------------------- //
    Publisher<Boolean> reportVideoToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportImageToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
    Publisher<Boolean> reportAudioToAdmin(@NonNull ObjectId id, @NonNull User user, @NonNull @NotBlank String content);
}