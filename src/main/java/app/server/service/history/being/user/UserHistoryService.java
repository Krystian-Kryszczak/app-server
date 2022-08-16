package app.server.service.history.being.user;

import app.server.model.history.being.user.UserHistory;
import app.server.service.history.HistoryService;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

 public interface UserHistoryService extends HistoryService<UserHistory> {
    // Post //
     Mono<Void> userVotePost(@NotBlank String userHexId, @NotBlank String postHexId, boolean up);
     Mono<Void> userCancelVotePost(@NotBlank String userHexId, @NotBlank String postHexId);
     Mono<Void> userCommentPost(@NotBlank String userHexId, @NotBlank String postHexId, @NotBlank String content);
     Mono<Void> userEditCommentPost(@NotBlank String userHexId, @NotBlank String postHexId, @NotBlank String content);
     Mono<Void> userEditCommentPost(@NotBlank String userHexId, @NotBlank String postHexId);
     Mono<Void> userVoteCommentPost(@NotBlank String userHexId, @NotBlank String postHexId, boolean up);
     Mono<Void> userCancelVoteCommentPost(@NotBlank String userHexId, @NotBlank String postHexId);
    // Page //
     Mono<Void> userVotePage(@NotBlank String userHexId, @NotBlank String pageHexId, boolean up);
     Mono<Void> userCancelVotePage(@NotBlank String userHexId, @NotBlank String pageHexId);
     Mono<Void> userCommentPage(@NotBlank String userHexId, @NotBlank String pageHexId, @NotBlank String content);
     Mono<Void> userEditCommentPage(@NotBlank String userHexId, @NotBlank String pageHexId, @NotBlank String content);
     Mono<Void> userEditCommentPage(@NotBlank String userHexId, @NotBlank String pageHexId);
     Mono<Void> userVoteCommentPage(@NotBlank String userHexId, @NotBlank String pageHexId, boolean up);
     Mono<Void> userCancelVoteCommentPage(@NotBlank String userHexId, @NotBlank String pageHexId);
    // Watch //
     Mono<Void> userVoteWatch(@NotBlank String userHexId, @NotBlank String watchHexId, boolean up);
     Mono<Void> userCancelVoteWatch(@NotBlank String userHexId, @NotBlank String watchHexId);
     Mono<Void> userCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId, @NotBlank String content);
     Mono<Void> userEditCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId, @NotBlank String content);
     Mono<Void> userEditCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId);
     Mono<Void> userVoteCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId, boolean up);
     Mono<Void> userCancelVoteCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId);
    // Reel //
     Mono<Void> userVoteReel(@NotBlank String userHexId, @NotBlank String reelHexId, boolean up);
     Mono<Void> userCancelVoteReel(@NotBlank String userHexId, @NotBlank String reelHexId);
     Mono<Void> userCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId, @NotBlank String content);
     Mono<Void> userEditCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId, @NotBlank String content);
     Mono<Void> userEditCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId);
     Mono<Void> userVoteCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId, boolean up);
     Mono<Void> userCancelVoteCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId);
    // Snap //
     Mono<Void> userVoteSnap(@NotBlank String userHexId, @NotBlank String snapHexId, boolean up);
     Mono<Void> userCancelVoteSnap(@NotBlank String userHexId, @NotBlank String snapHexId);
     Mono<Void> userCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId, @NotBlank String content);
     Mono<Void> userEditCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId, @NotBlank String content);
     Mono<Void> userEditCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId);
     Mono<Void> userVoteCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId, boolean up);
     Mono<Void> userCancelVoteCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId);
    // Story //
     Mono<Void> userVoteStory(@NotBlank String userHexId, @NotBlank String storyHexId, boolean up);
     Mono<Void> userCancelVoteStory(@NotBlank String userHexId, @NotBlank String storyHexId);
     Mono<Void> userCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId, @NotBlank String content);
     Mono<Void> userEditCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId, @NotBlank String content);
     Mono<Void> userEditCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId);
     Mono<Void> userVoteCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId, boolean up);
     Mono<Void> userCancelVoteCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId);
    // Song //
     Mono<Void> userVoteSong(@NotBlank String userHexId, @NotBlank String songHexId, boolean up);
     Mono<Void> userCancelVoteSong(@NotBlank String userHexId, @NotBlank String songHexId);
     Mono<Void> userCommentSong(@NotBlank String userHexId, @NotBlank String songHexId, @NotBlank String content);
     Mono<Void> userEditCommentSong(@NotBlank String userHexId, @NotBlank String songHexId, @NotBlank String content);
     Mono<Void> userEditCommentSong(@NotBlank String userHexId, @NotBlank String songHexId);
     Mono<Void> userVoteCommentSong(@NotBlank String userHexId, @NotBlank String songHexId, boolean up);
     Mono<Void> userCancelVoteCommentSong(@NotBlank String userHexId, @NotBlank String songHexId);
    // Album //
     Mono<Void> userVoteAlbum(@NotBlank String userHexId, @NotBlank String albumHexId, boolean up);
     Mono<Void> userCancelVoteAlbum(@NotBlank String userHexId, @NotBlank String albumHexId);
     Mono<Void> userCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId, @NotBlank String content);
     Mono<Void> userEditCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId, @NotBlank String content);
     Mono<Void> userEditCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId);
     Mono<Void> userVoteCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId, boolean up);
     Mono<Void> userCancelVoteCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId);
}