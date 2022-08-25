package app.server.model.history.exhibit.song;

import app.server.model.exhibit.song.Song;
import app.server.model.history.exhibit.ExhibitHistory;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Introspected
public class SongHistory extends ExhibitHistory<Song> {
    @Creator
    @BsonCreator
    protected SongHistory(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("user") ObjectId userId, @NonNull @BsonProperty("target") ObjectId target, @Nullable @BsonProperty("content") String content, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(id, userId, target, content, dateTime);
    }
    protected SongHistory(@NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(userId, target, content);
    }
}