package app.server.service.history.being.user;

import app.server.model.being.user.User;
import app.server.model.history.type.HistoryType;
import app.server.model.history.being.user.UserHistory;
import app.server.service.history.being.BeingHistoryServiceImpl;
import app.server.storage.repository.history.being.user.UserHistoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

@Singleton
public class UserHistoryServiceImpl extends BeingHistoryServiceImpl<User, UserHistory> implements UserHistoryService {
    @Inject
    UserHistoryRepository historyRepo;
    // Post //
    public Mono<Void> userVotePost(@NotBlank String userHexId, @NotBlank String postHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.PostVote, postHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVotePost(@NotBlank String userHexId, @NotBlank String postHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.PostCancelVote, postHexId));
    }
    public Mono<Void> userCommentPost(@NotBlank String userHexId, @NotBlank String postHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.PostComment, postHexId, content));
    }
    public Mono<Void> userEditCommentPost(@NotBlank String userHexId, @NotBlank String postHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.PostEditComment, postHexId, content));
    }
    public Mono<Void> userEditCommentPost(@NotBlank String userHexId, @NotBlank String postHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.PostDeleteComment, postHexId));
    }
    public Mono<Void> userVoteCommentPost(@NotBlank String userHexId, @NotBlank String postHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.PostVoteComment, postHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteCommentPost(@NotBlank String userHexId, @NotBlank String postHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.PostCancelVoteComment, postHexId));
    }
    // Watch //
    public Mono<Void> userVoteWatch(@NotBlank String userHexId, @NotBlank String watchHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.WatchVote, watchHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteWatch(@NotBlank String userHexId, @NotBlank String watchHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.WatchCancelVote, watchHexId));
    }
    public Mono<Void> userCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.WatchComment, watchHexId, content));
    }
    public Mono<Void> userEditCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.WatchEditComment, watchHexId, content));
    }
    public Mono<Void> userEditCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.WatchDeleteComment, watchHexId));
    }
    public Mono<Void> userVoteCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.WatchVoteComment, watchHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteCommentWatch(@NotBlank String userHexId, @NotBlank String watchHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.WatchCancelVoteComment, watchHexId));
    }
    // Reel //
    public Mono<Void> userVoteReel(@NotBlank String userHexId, @NotBlank String reelHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.ReelVote, reelHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteReel(@NotBlank String userHexId, @NotBlank String reelHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.ReelCancelVote, reelHexId));
    }
    public Mono<Void> userCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.ReelComment, reelHexId, content));
    }
    public Mono<Void> userEditCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.ReelEditComment, reelHexId, content));
    }
    public Mono<Void> userEditCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.ReelDeleteComment, reelHexId));
    }
    public Mono<Void> userVoteCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.ReelVoteComment, reelHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteCommentReel(@NotBlank String userHexId, @NotBlank String reelHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.ReelCancelVoteComment, reelHexId));
    }
    // Snap //
    public Mono<Void> userVoteSnap(@NotBlank String userHexId, @NotBlank String snapHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SnapVote, snapHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteSnap(@NotBlank String userHexId, @NotBlank String snapHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SnapCancelVote, snapHexId));
    }
    public Mono<Void> userCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SnapComment, snapHexId, content));
    }
    public Mono<Void> userEditCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SnapEditComment, snapHexId, content));
    }
    public Mono<Void> userEditCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SnapDeleteComment, snapHexId));
    }
    public Mono<Void> userVoteCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SnapVoteComment, snapHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteCommentSnap(@NotBlank String userHexId, @NotBlank String snapHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SnapCancelVoteComment, snapHexId));
    }
    // Story //
    public Mono<Void> userVoteStory(@NotBlank String userHexId, @NotBlank String storyHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.StoryVote, storyHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteStory(@NotBlank String userHexId, @NotBlank String storyHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.StoryCancelVote, storyHexId));
    }
    public Mono<Void> userCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.StoryComment, storyHexId, content));
    }
    public Mono<Void> userEditCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.StoryEditComment, storyHexId, content));
    }
    public Mono<Void> userEditCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.StoryDeleteComment, storyHexId));
    }
    public Mono<Void> userVoteCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.StoryVoteComment, storyHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteCommentStory(@NotBlank String userHexId, @NotBlank String storyHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.StoryCancelVoteComment, storyHexId));
    }
    // Song //
    public Mono<Void> userVoteSong(@NotBlank String userHexId, @NotBlank String songHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SongVote, songHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteSong(@NotBlank String userHexId, @NotBlank String songHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SongCancelVote, songHexId));
    }
    public Mono<Void> userCommentSong(@NotBlank String userHexId, @NotBlank String songHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SongComment, songHexId, content));
    }
    public Mono<Void> userEditCommentSong(@NotBlank String userHexId, @NotBlank String songHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SongEditComment, songHexId, content));
    }
    public Mono<Void> userEditCommentSong(@NotBlank String userHexId, @NotBlank String songHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SongDeleteComment, songHexId));
    }
    public Mono<Void> userVoteCommentSong(@NotBlank String userHexId, @NotBlank String songHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SongVoteComment, songHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteCommentSong(@NotBlank String userHexId, @NotBlank String songHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.SongCancelVoteComment, songHexId));
    }
    // Album //
    public Mono<Void> userVoteAlbum(@NotBlank String userHexId, @NotBlank String albumHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.AlbumVote, albumHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteAlbum(@NotBlank String userHexId, @NotBlank String albumHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.AlbumCancelVote, albumHexId));
    }
    public Mono<Void> userCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.AlbumComment, albumHexId, content));
    }
    public Mono<Void> userEditCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId, @NotBlank String content) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.AlbumEditComment, albumHexId, content));
    }
    public Mono<Void> userEditCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.AlbumDeleteComment, albumHexId));
    }
    public Mono<Void> userVoteCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.AlbumVoteComment, albumHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteCommentAlbum(@NotBlank String userHexId, @NotBlank String albumHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.AlbumCancelVoteComment, albumHexId));
    }
    // Comment //
    public Mono<Void> userVoteComment(@NotBlank String userHexId, @NotBlank String commentHexId, boolean up) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.CommentVote, commentHexId, String.valueOf(up)));
    }
    public Mono<Void> userCancelVoteComment(@NotBlank String userHexId, @NotBlank String commentHexId) {
        return historyRepo.addHistory(UserHistory.create(userHexId, HistoryType.User.CommentCancelVote, commentHexId));
    }
}