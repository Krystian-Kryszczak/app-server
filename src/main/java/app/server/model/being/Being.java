package app.server.model.being;

import app.server.model.StorageItem;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;

@Getter
public abstract class Being implements StorageItem {
    @Nullable
    @BsonId
    final ObjectId id;
    @NonNull @NotBlank
    @BsonProperty("name")
    final String name;
    @Creator
    @BsonCreator
    public Being(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    public Being(@NonNull String name) {
        this.id = null;
        this.name = name;
    }
}