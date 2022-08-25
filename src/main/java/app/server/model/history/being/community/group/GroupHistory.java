package app.server.model.history.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.model.history.being.community.CommunityHistory;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Introspected
public class GroupHistory extends CommunityHistory<Group> {
    @Creator
    @BsonCreator
    protected GroupHistory(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("user") ObjectId userId,
    @NonNull @BsonProperty("target") ObjectId target, @Nullable @BsonProperty("content") String content, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(id, userId, target, content, dateTime);
    }
    public GroupHistory(@NonNull ObjectId hexId, @NonNull ObjectId target, @NonNull String content) {
        super(hexId, target, content);
    }
}