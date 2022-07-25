package app.server.model.history.group;

import app.server.model.history.History;
import io.micronaut.core.annotation.NonNull;

public class GroupHistory extends History {
    public GroupHistory(@NonNull String hexId, @NonNull String target, @NonNull String content) {
        super(hexId, target, content);
    }
}