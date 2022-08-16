package app.server.model.rules.group;

import app.server.model.rules.Rules;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Introspected
public class GroupRules extends Rules {
    @Creator
    @BsonCreator
    public GroupRules(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("targetHexId") String targetHexId, @NonNull @BsonProperty("content") String content) {
        super(id, targetHexId, content);
    }
    public GroupRules(@NonNull String targetHexId, @NonNull String content) {
        super(targetHexId, content);
    }
}