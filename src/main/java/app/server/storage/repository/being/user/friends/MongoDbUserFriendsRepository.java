package app.server.storage.repository.being.user.friends;

import app.server.model.being.user.friends.UserFriends;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.*;

@Singleton
public class MongoDbUserFriendsRepository extends ExtendedMongoDbRepository<UserFriends> implements UserFriendsRepository {
    public MongoDbUserFriendsRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getUserFriendsCollection(), UserFriends.class);
    }
    @Override
    public Mono<Boolean> addUserToFriends(@NonNull String userHexId, @NonNull String friendHexId) {
        if (userHexId.equals(friendHexId)) return Mono.just(false);
        return Mono.from(getDocCollection().find(getBsonEq_userId(userHexId)) // find userFriends by userId
            .projection(Projections.fields(elemMatch("friends", exists(friendHexId, false)))).first()) // check if list not contains friend id
                .then(Mono.from(getDocCollection().findOneAndUpdate(getBsonEq_userId(userHexId), Updates.addToSet("friends", friendHexId)))
                    .thenReturn(true)).defaultIfEmpty(false);
    }
    @Override
    public Mono<Boolean> removeUserFromFriends(@NonNull String userHexId, @NonNull String friendHexId) {
        if (userHexId.equals(friendHexId)) return Mono.just(false);
        return Mono.from(getDocCollection().find(getBsonEq_userId(userHexId)) // find userFriends by userId
            .projection(Projections.fields(elemMatch("friends", exists(friendHexId, true)))).first()) // check if list contains friend id
                .then(Mono.from(getDocCollection().findOneAndUpdate(getBsonEq_userId(userHexId), Updates.pull("friends", friendHexId)))
                    .thenReturn(true)).defaultIfEmpty(false);
    }
    public Mono<UserFriends> findByUserHexId(@NonNull String userHexId) {
        return Mono.from(getCollection().find(eq("userHexId",  userHexId)));
    }
    public Flux<String> findFriendsByUserId(@NonNull String userHexId) {
        if (ObjectId.isValid(userHexId)) throw new RuntimeException("Invalid userHexId ("+userHexId+").");
        return Mono.from(getDocCollection().find(getBsonEq_userId(userHexId))).flatMapIterable(doc -> doc.getList("friends", String.class));
    }
    protected Bson getBsonEq_userId(String userHexId) {
        return eq("userHexId",  userHexId);
    }
}