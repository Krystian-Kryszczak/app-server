package app.server.storage.repository.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.being.community.MongoDbCommunityRepository;
import com.mongodb.client.model.Aggregates;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;
import org.bson.Document;
import reactor.core.publisher.Flux;

import java.util.List;

@Singleton
public class MongoDbGroupRepository extends MongoDbCommunityRepository<Group> implements GroupRepository {
    public MongoDbGroupRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getGroupCollection(), Group.class);
    }
    @Override
    public Flux<Document> getGroupsRecommendation(String clientHexId) {
        return Flux.from(getDocCollection().aggregate(List.of(Aggregates.sample(12))));
    }
}