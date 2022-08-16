package app.server.model.history.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.model.history.History;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class ExhibitHistory<T extends Exhibit<T>> extends History {
    @Creator
    @BsonCreator
    protected ExhibitHistory(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("user") ObjectId userId, @NonNull @BsonProperty("target") ObjectId target, @Nullable @BsonProperty("content") String content) {
        super(id, userId, target, content);
    }
    protected ExhibitHistory(@NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(userId, target, content);
    }
}