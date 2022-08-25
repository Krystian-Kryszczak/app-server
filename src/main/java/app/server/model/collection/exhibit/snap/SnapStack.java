package app.server.model.collection.exhibit.snap;

import app.server.model.collection.exhibit.ExhibitCollection;
import app.server.model.exhibit.snap.Snap;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Introspected
public class SnapStack extends ExhibitCollection<Snap> {
    @Creator
    @BsonCreator
    public SnapStack(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("performer") String performer, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("items") List<Snap> items) {
        super(id, performer, name, items);
    }
    public SnapStack(@NonNull String performer, @NonNull String name, @NonNull List<Snap> items) {
        super(performer, name, items);
    }
    public SnapStack(@NonNull String performer, @NonNull String name) {
        super(performer, name);
    }
}