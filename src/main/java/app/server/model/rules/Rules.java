package app.server.model.rules;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
public abstract class Rules {
    @Nullable
    @BsonId
    final ObjectId id;
    @NonNull
    @BsonProperty("targetHexId")
    final String targetHexId;
    @NonNull
    @BsonProperty("content")
    final String content;
    @Creator
    @BsonCreator
    protected Rules(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("targetHexId") String targetHexId, @NonNull @BsonProperty("content") String content) {
        this.id = id;
        this.targetHexId = targetHexId;
        this.content = content;
    }
    protected Rules(@NonNull String targetHexId, @NonNull String content) {
        this.id = null;
        this.targetHexId = targetHexId;
        this.content = content;
    }
}