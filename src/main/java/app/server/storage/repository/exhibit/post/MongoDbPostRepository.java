package app.server.storage.repository.exhibit.post;

import app.server.model.exhibit.post.Post;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.exhibit.ExhibitMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbPostRepository extends ExhibitMongoDbRepository<Post> implements PostRepository {
    public MongoDbPostRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getPostCollection(), Post.class);
    }
}