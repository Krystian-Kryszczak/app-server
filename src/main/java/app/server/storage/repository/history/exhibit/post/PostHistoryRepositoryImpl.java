package app.server.storage.repository.history.exhibit.post;

import app.server.model.exhibit.post.Post;
import app.server.model.history.exhibit.post.PostHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.exhibit.MongoDbExhibitHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class PostHistoryRepositoryImpl extends MongoDbExhibitHistoryRepository<Post, PostHistory> implements PostHistoryRepository{
    public PostHistoryRepositoryImpl(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getPostHistoryCollection(), PostHistory.class);
    }
}