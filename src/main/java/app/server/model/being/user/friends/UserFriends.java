package app.server.model.being.user.friends;

import app.server.model.being.user.User;
import app.server.storage.repository.being.user.friends.UserFriendsRepository;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import lombok.Builder;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

import java.util.Set;

@Getter
@Builder
@Introspected
public class UserFriends {
    @Inject
    static UserFriendsRepository userFriendsRepository;
    // ---------------------------------------------------------------------------------------------------- //
    @Nullable
    @BsonId
    private final ObjectId id;
    @NonNull
    @BsonProperty("userId")
    private final ObjectId userId;
    @NonNull
    @BsonProperty("friends")
    private final Set<String> friends;
    @Creator
    @BsonCreator
    public UserFriends(@Nullable @BsonId ObjectId id, @NonNull @BsonProperty("userId") ObjectId userId, @NonNull @BsonProperty("friends") Set<String> friends) {
        this.id = id;
        this.userId = userId;
        this.friends = friends;
    }
    public UserFriends(@NonNull ObjectId userId, @NonNull Set<String> friends) {
        this.id = null;
        this.userId = userId;
        this.friends = friends;
    }
    public UserFriends(@NonNull ObjectId userId) {
        this.id = null;
        this.userId = userId;
        this.friends = Set.of();
    }
    public Mono<Boolean> add(@NonNull User user) {
        if (user.getId()==null) return Mono.just(false);
        return add(user.getId());
    }
    public Mono<Boolean> add(@NonNull ObjectId friendId) {
        if (friendId.equals(userId)) return Mono.just(false);
        return userFriendsRepository.addUserToFriends(getUserId(), friendId)
            .map(result -> result && friends.add(friendId.toHexString()));
    }
    public Mono<Boolean> remove(@NonNull User user) {
        if (user.getId()==null) return Mono.just(false);
        return remove(user.getId());
    }
    public Mono<Boolean> remove(@NonNull ObjectId friendId) {
        if (friendId.equals(userId)) return Mono.just(false);
        return userFriendsRepository.removeUserFromFriends(getUserId(), friendId)
            .map(result -> result && friends.remove(friendId.toHexString()));
    }
}