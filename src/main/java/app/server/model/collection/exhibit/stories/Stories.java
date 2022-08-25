package app.server.model.collection.exhibit.stories;

import app.server.model.collection.exhibit.ExhibitCollection;
import app.server.model.exhibit.story.Story;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Introspected
public class Stories extends ExhibitCollection<Story> {
    @Creator
    @BsonCreator
    public Stories(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("performer") String performer, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("items") List<Story> items) {
        super(id, performer, name, items);
    }
    public Stories(@NonNull String performer, @NonNull String name, @NonNull List<Story> items) {
        super(performer, name, items);
    }
    public Stories(@NonNull String performer, @NonNull String name) {
        super(performer, name);
    }
}