package app.server.storage.repository.history.exhibit.story;

import app.server.model.exhibit.story.Story;
import app.server.model.history.exhibit.story.StoryHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.exhibit.MongoDbExhibitHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class StoryHistoryRepositoryImpl extends MongoDbExhibitHistoryRepository<Story, StoryHistory> implements StoryHistoryRepository {
    public StoryHistoryRepositoryImpl(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getStoryHistoryCollection(), StoryHistory.class);
    }
}