package app.server.model.collection;

import app.server.model.StorageItem;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Getter
public abstract class Collection<T> implements StorageItem {
    @Nullable
    @BsonId
    final ObjectId id;
    protected Collection(@Nullable ObjectId id) {
        this.id = id;
    }
}