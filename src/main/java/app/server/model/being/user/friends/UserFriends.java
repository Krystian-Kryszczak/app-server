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
    @BsonProperty("userHexId")
    private final String userId;
    @NonNull
    @BsonProperty("friends")
    private final Set<String> friends;
    @Creator
    @BsonCreator
    public UserFriends(@Nullable @BsonId ObjectId id, @NonNull @BsonProperty("userId") String userHexId, @NonNull @BsonProperty("friends") Set<String> friends) {
        this.id = id;
        this.userId = userHexId;
        this.friends = friends;
    }
    public UserFriends(@NonNull String userHexId, @NonNull Set<String> friends) {
        this.id = null;
        this.userId = userHexId;
        this.friends = friends;
    }
    public UserFriends(@NonNull String userHexId) {
        this.id = null;
        this.userId = userHexId;
        this.friends = Set.of();
    }
    public Mono<Boolean> add(@NonNull User user) {
        if (user.getId()==null) return Mono.just(false);
        return add(user.getId().toHexString());
    }
    public Mono<Boolean> add(@NonNull String friendHexId) {
        if (friendHexId.equals(userId)) return Mono.just(false);
        return userFriendsRepository.addUserToFriends(getUserId(), friendHexId)
            .map(result -> result && friends.add(friendHexId));
    }
    public Mono<Boolean> remove(@NonNull User user) {
        if (user.getId()==null) return Mono.just(false);
        return remove(user.getId().toHexString());
    }
    public Mono<Boolean> remove(@NonNull String friendHexId) {
        if (friendHexId.equals(userId)) return Mono.just(false);
        return userFriendsRepository.removeUserFromFriends(getUserId(), friendHexId)
            .map(result -> result && friends.remove(friendHexId));
    }
}