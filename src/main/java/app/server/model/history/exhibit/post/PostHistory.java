package app.server.model.history.exhibit.post;

import app.server.model.exhibit.post.Post;
import app.server.model.history.exhibit.ExhibitHistory;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.types.ObjectId;

@Introspected
public class PostHistory extends ExhibitHistory<Post> {
    @Creator
    @BsonCreator
    protected PostHistory(@NonNull ObjectId id, @NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(id, userId, target, content);
    }
    protected PostHistory(@NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(userId, target, content);
    }
}