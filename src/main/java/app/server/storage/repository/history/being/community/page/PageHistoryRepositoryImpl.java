package app.server.storage.repository.history.being.community.page;

import app.server.model.being.community.page.Page;
import app.server.model.history.being.community.page.PageHistory;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.history.being.community.MongoDbCommunityHistoryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class PageHistoryRepositoryImpl extends MongoDbCommunityHistoryRepository<Page, PageHistory> implements PageHistoryRepository {
    public PageHistoryRepositoryImpl(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getPageHistoryCollection(), PageHistory.class);
    }
}