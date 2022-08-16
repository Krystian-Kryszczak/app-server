package app.server.storage.repository.rules.group;

import app.server.model.rules.group.GroupRules;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.rules.MongoDbRulesRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbGroupRulesRepository extends MongoDbRulesRepository<GroupRules> implements GroupRulesRepository {
    public MongoDbGroupRulesRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getGroupRulesCollection(), GroupRules.class);
    }
}