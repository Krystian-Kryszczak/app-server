package app.server.model.exhibit;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Introspected
public abstract class MediaExhibit<T extends MediaExhibit<T>> extends Exhibit<T> {
    @NonNull @Getter
    @BsonProperty("media")
    final String media;
    @Creator
    @BsonCreator
    public MediaExhibit(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("userHexId") String userHexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("media") String media, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(id, userHexId, rating, dateTime);
        this.media = media;
    }
    public MediaExhibit(@NonNull String userHexId, @NonNull String media) {
        super(userHexId);
        this.media = media;
    }
}