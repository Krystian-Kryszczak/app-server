package app.server.storage.repository.exhibit.story;

import app.server.model.exhibit.story.Story;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.exhibit.ExhibitMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;

public class MongoDbStoryRepository extends ExhibitMongoDbRepository<Story> implements StoryRepository {
    public MongoDbStoryRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getStoryCollection(), Story.class);
    }
}