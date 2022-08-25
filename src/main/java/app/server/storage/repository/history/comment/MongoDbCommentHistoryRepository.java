package app.server.storage.repository.history.comment;

import app.server.model.comment.Comment;
import app.server.model.history.comment.CommentHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.MongoDbHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbCommentHistoryRepository extends MongoDbHistoryRepository<Comment, CommentHistory> implements CommentHistoryRepository {
    public MongoDbCommentHistoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getCommentHistoryCollection(), CommentHistory.class);
    }
}