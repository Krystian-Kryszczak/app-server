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

import java.util.List;

import static com.mongodb.client.model.Filters.*;

@Singleton
public class MongoDbUserFriendsRepository extends ExtendedMongoDbRepository<UserFriends> implements UserFriendsRepository {
    public MongoDbUserFriendsRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getUserFriendsCollection(), UserFriends.class);
    }
    @Override
    public Mono<Boolean> addUserToFriends(@NonNull ObjectId userId, @NonNull ObjectId friendId) {
        if (userId.equals(friendId)) return Mono.just(false);
        return Mono.from(getDocCollection().find(getBsonEq_userId(userId)) // find userFriends by userId
            .projection(Projections.fields(elemMatch("friends", exists(friendId.toHexString(), false)))).first()) // check if list not contains friend id
                .then(Mono.from(getDocCollection().findOneAndUpdate(getBsonEq_userId(userId), Updates.addToSet("friends", friendId.toHexString())))
                    .thenReturn(true)).defaultIfEmpty(false);
    }
    @Override
    public Mono<Boolean> removeUserFromFriends(@NonNull ObjectId userId, @NonNull ObjectId friendId) {
        if (userId.equals(friendId)) return Mono.just(false);
        return Mono.from(getDocCollection().find(getBsonEq_userId(userId)) // find userFriends by userId
            .projection(Projections.fields(elemMatch("friends", exists(friendId.toHexString(), true)))).first()) // check if list contains friend id
                .then(Mono.from(getDocCollection().findOneAndUpdate(getBsonEq_userId(userId), Updates.pull("friends", friendId.toHexString())))
                    .thenReturn(true)).defaultIfEmpty(false);
    }
    public Mono<UserFriends> findByUserId(@NonNull ObjectId objectId) {
        return Mono.from(getCollection().find(eq("userId",  objectId)));
    }
    public Flux<String> findFriendsByUserId(@NonNull String userHexId) {
        if (ObjectId.isValid(userHexId)) throw new RuntimeException("Invalid userHexId ("+userHexId+").");
        return Mono.from(getDocCollection().find(getBsonEq_userId(new ObjectId(userHexId)))).flatMapIterable(doc -> doc.getList("friends", String.class));
    }
    protected Bson getBsonEq_userId(ObjectId userId) {
        return eq("userId",  userId);
    }
}