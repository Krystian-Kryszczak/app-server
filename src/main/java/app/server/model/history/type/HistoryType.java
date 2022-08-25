package app.server.model.history.type;

public enum HistoryType {
    Created;
    public enum User {
        // Page //
            PageVote,
            PageCancelVote,
            PageComment,
            PageEditComment,
            PageDeleteComment,
            PageVoteComment,
            PageCancelVoteComment,
        // Post //
            PostVote,
            PostCancelVote,
            PostComment,
            PostEditComment,
            PostDeleteComment,
            PostVoteComment,
            PostCancelVoteComment,
        // Watch //
            WatchVote,
            WatchCancelVote,
            WatchComment,
            WatchEditComment,
            WatchDeleteComment,
            WatchVoteComment,
            WatchCancelVoteComment,
        // Reel //
            ReelVote,
            ReelCancelVote,
            ReelComment,
            ReelEditComment,
            ReelDeleteComment,
            ReelVoteComment,
            ReelCancelVoteComment,
        // Snap //
            SnapVote,
            SnapCancelVote,
            SnapComment,
            SnapEditComment,
            SnapDeleteComment,
            SnapVoteComment,
            SnapCancelVoteComment,
        // Story //
            StoryVote,
            StoryCancelVote,
            StoryComment,
            StoryEditComment,
            StoryDeleteComment,
            StoryVoteComment,
            StoryCancelVoteComment,
        // Song //
            SongVote,
            SongCancelVote,
            SongComment,
            SongEditComment,
            SongDeleteComment,
            SongVoteComment,
            SongCancelVoteComment,
        // Album //
            AlbumVote,
            AlbumCancelVote,
            AlbumComment,
            AlbumEditComment,
            AlbumDeleteComment,
            AlbumVoteComment,
            AlbumCancelVoteComment,
        // Comment //
        CommentVote, CommentCancelVote,
    }
    public enum Group {}
}