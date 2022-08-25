package app.server.storage.repository.being.user.friends;

import app.server.model.being.user.friends.UserFriends;
import app.server.storage.repository.Repository;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserFriendsRepository extends Repository<UserFriends> {
    Mono<Boolean> addUserToFriends(@NonNull String userHexId, @NonNull String friendHexId);
    Mono<Boolean> removeUserFromFriends(@NonNull String userHexId, @NonNull String friendHexId);
    Mono<UserFriends> findByUserHexId(@NonNull String userHexId);
    Flux<String> findFriendsByUserId(@NonNull String userHexId);
}