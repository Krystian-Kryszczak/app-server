package app.server.storage.repository.exhibit.page;

import app.server.model.exhibit.page.Page;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.exhibit.ExhibitMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbPageRepository extends ExhibitMongoDbRepository<Page> implements PageRepository {
    public MongoDbPageRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getPageCollection(), Page.class);
    }
}