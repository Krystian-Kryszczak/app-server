package app.server.model.collection.exhibit.reel;

import app.server.model.collection.exhibit.ExhibitCollection;
import app.server.model.exhibit.reel.Reel;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Introspected
public class Reels extends ExhibitCollection<Reel> {
    @Creator
    @BsonCreator
    public Reels(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("performer") String performer, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("items") List<Reel> items) {
        super(id, performer, name, items);
    }
    public Reels(@NonNull String performer, @NonNull String name, @NonNull List<Reel> items) {
        super(performer, name, items);
    }
    public Reels(@NonNull String performer, @NonNull String name) {
        super(performer, name);
    }
}