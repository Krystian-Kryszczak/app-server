package app.server.model.history.exhibit.reel;

import app.server.model.exhibit.reel.Reel;
import app.server.model.history.exhibit.ExhibitHistory;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.types.ObjectId;

@Introspected
public class ReelHistory extends ExhibitHistory<Reel> {
    @Creator
    @BsonCreator
    protected ReelHistory(@NonNull ObjectId id, @NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(id, userId, target, content);
    }
    protected ReelHistory(@NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(userId, target, content);
    }
}