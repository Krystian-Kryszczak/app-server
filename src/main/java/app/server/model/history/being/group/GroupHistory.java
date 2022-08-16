package app.server.model.history.being.group;

import app.server.model.history.History;
import io.micronaut.core.annotation.NonNull;
import org.bson.types.ObjectId;

public class GroupHistory extends History {
    public GroupHistory(@NonNull ObjectId hexId, @NonNull ObjectId target, @NonNull String content) {
        super(hexId, target, content);
    }
}