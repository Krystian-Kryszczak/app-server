package app.server.model.collection.exhibit.song;

import app.server.model.collection.exhibit.ExhibitCollection;
import app.server.model.exhibit.song.Song;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Introspected
public class Album extends ExhibitCollection<Song> {
    @Creator
    @BsonCreator
    public Album(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("performer") String performer, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("items") List<Song> items) {
        super(id, performer, name, items);
    }
    public Album(@NonNull String performer, @NonNull String name, @NonNull List<Song> items) {
        super(performer, name, items);
    }
    public Album(@NonNull String performer, @NonNull String name) {
        super(performer, name);
    }
}