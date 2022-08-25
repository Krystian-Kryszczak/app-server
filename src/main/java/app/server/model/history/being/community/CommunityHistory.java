package app.server.model.history.being.community;

import app.server.model.being.community.Community;
import app.server.model.history.being.BeingHistory;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public abstract class CommunityHistory<T extends Community> extends BeingHistory<T> {
    @Creator
    @BsonCreator
    public CommunityHistory(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("user") ObjectId userId, @NonNull @BsonProperty("target") ObjectId target,
    @Nullable @BsonProperty("content") String content, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(id, userId, target, content, dateTime);
    }
    public CommunityHistory(@NonNull ObjectId hexId, @NonNull ObjectId target, @NonNull String content) {
        super(hexId, target, content);
    }
}