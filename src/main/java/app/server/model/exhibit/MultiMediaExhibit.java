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
import java.util.List;

@Introspected
public abstract class MultiMediaExhibit<T extends MultiMediaExhibit<T>> extends Exhibit<T> {
    @NonNull @Getter
    @BsonProperty("media")
    final List<String> media;
    @Creator
    @BsonCreator
    public MultiMediaExhibit(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("userHexId") String userHexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("media") List<String> media, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(id, userHexId, rating, dateTime);
        this.media = media;
    }
    public MultiMediaExhibit(@NonNull String userHexId, @NonNull List<String> media) {
        super(userHexId);
        this.media = media;
    }
}