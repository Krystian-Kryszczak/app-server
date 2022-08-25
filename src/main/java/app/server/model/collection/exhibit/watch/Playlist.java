package app.server.model.collection.exhibit.watch;

import app.server.model.collection.exhibit.ExhibitCollection;
import app.server.model.exhibit.watch.Watch;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Introspected
public class Playlist extends ExhibitCollection<Watch> {
    @Creator
    @BsonCreator
    public Playlist(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("performer") String performer, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("items") List<Watch> items) {
        super(id, performer, name, items);
    }
    public Playlist(@NonNull String performer, @NonNull String name, @NonNull List<Watch> items) {
        super(performer, name, items);
    }
    public Playlist(@NonNull String performer, @NonNull String name) {
        super(performer, name);
    }
}