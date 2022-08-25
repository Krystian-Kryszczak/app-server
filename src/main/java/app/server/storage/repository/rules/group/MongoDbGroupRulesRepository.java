package app.server.storage.repository.rules.group;

import app.server.model.rules.group.GroupRules;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.rules.MongoDbRulesRepository;
import com.mongodb.client.model.Updates;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class MongoDbGroupRulesRepository extends MongoDbRulesRepository<GroupRules> implements GroupRulesRepository {
    public MongoDbGroupRulesRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getGroupRulesCollection(), GroupRules.class);
    }
    @Override
    public Mono<GroupRules> findByTargetId(String ownerHexId) {
        return Mono.from(getCollection().find(getBsonEq_targetId(ownerHexId)));
    }
    @Override
    public Mono<GroupRules> deleteByTargetId(String ownerHexId) {
        return Mono.from(getCollection().findOneAndDelete(getBsonEq_targetId(ownerHexId)));
    }
    @Override
    public Mono<Boolean> findByTargetIdAndSetContent(String targetHexId, String content) {
        return Mono.from(getDocCollection().findOneAndUpdate(getBsonEq_targetId(targetHexId), Updates.set("content", content))).thenReturn(true).onErrorReturn(false);
    }
    protected Bson getBsonEq_targetId(String ownerHexId) {
        return eq("targetHexId", new ObjectId(ownerHexId));
    }
}