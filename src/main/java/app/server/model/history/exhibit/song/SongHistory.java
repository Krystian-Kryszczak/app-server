package app.server.model.history.exhibit.song;

import app.server.model.exhibit.song.Song;
import app.server.model.history.exhibit.ExhibitHistory;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.types.ObjectId;

@Introspected
public class SongHistory extends ExhibitHistory<Song> {
    @Creator
    @BsonCreator
    protected SongHistory(@NonNull ObjectId id, @NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(id, userId, target, content);
    }
    protected SongHistory(@NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(userId, target, content);
    }
}