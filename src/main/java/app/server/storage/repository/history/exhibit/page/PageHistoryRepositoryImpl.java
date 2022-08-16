package app.server.storage.repository.history.exhibit.page;

import app.server.model.exhibit.page.Page;
import app.server.model.history.exhibit.page.PageHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.exhibit.MongoDbExhibitHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class PageHistoryRepositoryImpl extends MongoDbExhibitHistoryRepository<Page, PageHistory> implements PageHistoryRepository {
    public PageHistoryRepositoryImpl(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getPageHistoryCollection(), PageHistory.class);
    }
}