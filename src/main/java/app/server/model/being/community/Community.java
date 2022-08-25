package app.server.model.being.community;

import app.server.model.being.Being;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public abstract class Community extends Being {
    @Creator
    @BsonCreator
    public Community(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("name") String name) {
        super(id, name);
    }
    public Community(@NonNull String name) {
        super(name);
    }
}