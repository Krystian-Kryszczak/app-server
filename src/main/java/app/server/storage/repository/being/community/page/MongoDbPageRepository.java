package app.server.storage.repository.being.community.page;

import app.server.model.being.community.page.Page;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.being.community.MongoDbCommunityRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbPageRepository extends MongoDbCommunityRepository<Page> implements PageRepository {
    public MongoDbPageRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getPageCollection(), Page.class);
    }
}