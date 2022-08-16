package app.server.model.being;

import app.server.model.StorageItem;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public abstract class Being implements StorageItem { // notifications on|off
    @BsonId
    final ObjectId id;
    @Creator
    @BsonCreator
    public Being(@NonNull @BsonId ObjectId id) {
        this.id = id;
    }
    public Being() {
        this.id = null;
    }
    @Nullable
    public ObjectId getId() {
        return id;
    }
}