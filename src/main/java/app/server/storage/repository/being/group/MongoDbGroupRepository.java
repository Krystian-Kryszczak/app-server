package app.server.storage.repository.being.group;

import app.server.model.being.group.Group;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.being.BeingMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbGroupRepository extends BeingMongoDbRepository<Group> implements GroupRepository {
    public MongoDbGroupRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getGroupCollection(), Group.class);
    }
}