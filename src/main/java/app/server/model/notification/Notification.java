package app.server.model.notification;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Introspected
public class Notification {
    @Nullable
    @BsonId
    final ObjectId id;
    @NonNull
    @BsonProperty("target")
    final Target target;
    @NonNull
    @BsonProperty("targetHexId")
    final String targetHexId;
    @NonNull
    @BsonProperty("content")
    final String content;
    @NonNull
    @BsonProperty("dateTime")
    final LocalDateTime dateTime;
    @Creator
    @BsonCreator
    public Notification(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("target") Target target, @NonNull @BsonProperty("targetHexId") String targetHexId, @NonNull @BsonProperty("content") String content, @NonNull @BsonProperty("dateTime") LocalDateTime dateTime) {
        this.id = id;
        this.target = target;
        this.targetHexId = targetHexId;
        this.content = content;
        this.dateTime = dateTime;
    }
    public Notification(@NonNull Target target, @NonNull String targetHexId, @NonNull String content) {
        this.id = null;
        this.target = target;
        this.targetHexId = targetHexId;
        this.content = content;
        this.dateTime = LocalDateTime.now();
    }
    public enum Target {
        group,
        user,
    }
}