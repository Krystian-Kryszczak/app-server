package app.server.model.media;

import app.server.model.being.user.User;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.BsonBinarySubType;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

@Getter
@Introspected
public abstract class Media<T extends Media<T>> {
    @BsonId
    final ObjectId id;
    @NonNull
    @BsonProperty("name")
    final String name;
    @NonNull
    @BsonProperty("creatorHexId")
    final String creatorHexId;
    @Nullable
    @BsonProperty("isPrivate")
    final Boolean isPrivate;
    @NonNull
    @BsonProperty("dateTime")
    final LocalDateTime dateTime;
    @NonNull
    @BsonProperty("binary")
    final Binary binary;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Media(
        @Nullable @BsonId ObjectId id, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("creatorHexId") String creatorHexId,
        @Nullable @BsonProperty("isPrivate") Boolean isPrivate, @NonNull @BsonProperty("dateTime") LocalDateTime dateTime,
        @NonNull @BsonProperty("binary") Binary binary) {
        this.id = id;
        this.name = name;
        this.creatorHexId = creatorHexId;
        this.isPrivate = isPrivate;
        this.dateTime = dateTime;
        this.binary = binary;
    }
    public Media(@NonNull String name, @NonNull String creatorHexId, @Nullable Boolean isPrivate, @NonNull Binary binary) {
        this.id = null;
        this.name = name;
        this.creatorHexId = creatorHexId;
        this.isPrivate = isPrivate;
        this.dateTime = LocalDateTime.now();
        this.binary = binary;
    }
    @Deprecated
    public Media(@NonNull String name, @NonNull String creatorHexId, @Nullable Boolean isPrivate, @NonNull File file) throws IOException {
        this.id = null;
        this.name = name;
        this.creatorHexId = creatorHexId;
        this.isPrivate = isPrivate;
        this.dateTime = LocalDateTime.now();
        this.binary = new Binary(BsonBinarySubType.BINARY, Files.readAllBytes(file.toPath()));
    }
    // ---------------------------------------------------------------------------------------------------- //
    public abstract String getMediaUrl();
    // ---------------------------------------------------------------------------------------------------- //
    public abstract Publisher<Boolean> report(@NonNull User user, @NonNull @NotBlank String content);
    // ---------------------------------------------------------------------------------------------------- //
    public abstract Publisher<T> delete();
    // ---------------------------------------------------------------------------------------------------- //
}