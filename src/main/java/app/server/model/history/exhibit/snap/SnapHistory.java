package app.server.model.history.exhibit.snap;

import app.server.model.exhibit.snap.Snap;
import app.server.model.history.exhibit.ExhibitHistory;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.types.ObjectId;

@Introspected
public class SnapHistory extends ExhibitHistory<Snap> {
    @Creator
    @BsonCreator
    protected SnapHistory(@NonNull ObjectId id, @NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(id, userId, target, content);
    }
    protected SnapHistory(@NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(userId, target, content);
    }
}