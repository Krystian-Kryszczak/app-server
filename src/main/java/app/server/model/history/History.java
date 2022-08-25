package app.server.model.history;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
public abstract class History {
    @Nullable
    @BsonId
    final ObjectId id;
    @NonNull
    @BsonProperty("datetime")
    final LocalDateTime dateTime;
    @NonNull
    @BsonProperty("user")
    final ObjectId userId;
    @NonNull
    @BsonProperty("target")
    final ObjectId target; //
    @Nullable
    @BsonProperty("content")
    final String content;
    @Creator
    @BsonCreator
    protected History(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("user") ObjectId userId, @NonNull @BsonProperty("target") ObjectId target, @Nullable @BsonProperty("content") String content, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        this.id = id;
        this.dateTime = dateTime;
        this.userId = userId;
        this.target = target;
        this.content = content;
    }
    protected History(@NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        this.id = null;
        this.dateTime = LocalDateTime.now();
        this.userId = userId;
        this.target = target;
        this.content = content;
    }
}