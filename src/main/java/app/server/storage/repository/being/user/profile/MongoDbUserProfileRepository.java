package app.server.storage.repository.being.user.profile;

import app.server.model.being.user.profile.UserProfile;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;

@Singleton
public class MongoDbUserProfileRepository extends ExtendedMongoDbRepository<UserProfile> implements UserProfileRepository {
    public MongoDbUserProfileRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getUserProfileCollection(), UserProfile.class);
    }
}