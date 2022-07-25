package app.server.model.media;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.Binary;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Introspected
public abstract class Streamable<T extends Media<T>> extends Media<T> {
    @Creator
    @BsonCreator
    public Streamable(
            @Nullable @BsonId String hexId, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("creatorHexId") String creatorHexId,
            @NonNull @BsonProperty("dateTime") LocalDateTime dateTime, @NonNull @BsonProperty("binary") Binary binary) {
        super(hexId, name, creatorHexId, dateTime, binary);
    }
    public Streamable(@NonNull String name, @NonNull String creatorHexId, @NonNull Binary binary) {
        super(name, creatorHexId, binary);
    }
    @Deprecated
    public Streamable(@NonNull String name, @NonNull String creatorHexId, @NonNull File file) throws IOException {
        super(name, creatorHexId, file);
    }
}