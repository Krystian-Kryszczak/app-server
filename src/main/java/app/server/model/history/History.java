package app.server.model.history;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDateTime;

@Getter
public abstract class History {
    @Nullable
    @BsonId
    final String hexId;
    @NonNull
    @BsonProperty("datetime")
    final LocalDateTime dateTime;
    @NonNull
    @BsonProperty("user")
    final String userHexId;
    @NonNull
    @BsonProperty("target")
    final String target; // HexId
    @Nullable
    @BsonProperty("content")
    final String content;
    @Creator
    @BsonCreator
    protected History(@NonNull @BsonId String hexId, @NonNull @BsonProperty("user") String userHexId, @NonNull @BsonProperty("target") String target, @Nullable @BsonProperty("content") String content) {
        this.hexId = hexId;
        this.dateTime = LocalDateTime.now();
        this.userHexId = userHexId;
        this.target = target;
        this.content = content;
    }
    protected History(@NonNull String userHexId, @NonNull String target, @Nullable String content) {
        this.hexId = null;
        this.dateTime = LocalDateTime.now();
        this.userHexId = userHexId;
        this.target = target;
        this.content = content;
    }
}