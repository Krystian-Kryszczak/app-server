package app.server.storage.repository.being.user.friends;

import app.server.model.being.user.friends.UserFriends;
import app.server.storage.repository.Repository;
import io.micronaut.core.annotation.NonNull;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserFriendsRepository extends Repository<UserFriends> {
    Mono<Boolean> addUserToFriends(@NonNull ObjectId userId, @NonNull ObjectId friendId);
    Mono<Boolean> removeUserFromFriends(@NonNull ObjectId userId, @NonNull ObjectId friendId);
    Mono<UserFriends> findByUserId(@NonNull ObjectId objectId);
    Mono<List<String>> findFriendsListByUserId(@NonNull ObjectId objectId);
}