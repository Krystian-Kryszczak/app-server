package app.server.model.being.user.profile;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Introspected
public class UserProfile {
    @BsonId
    @Nullable
    ObjectId id;
    @NonNull
    @BsonProperty("userHexId")
    String userHexId;
    @Creator
    @BsonCreator
    public UserProfile(@Nullable ObjectId id, @NonNull String userHexId) {
        this.id = id;
        this.userHexId = userHexId;
    }
}