package app.server.model.report;

import app.server.model.being.user.User;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Introspected
public abstract class Report {
    @Creator
    @BsonCreator
    public Report(@BsonId ObjectId id, @NonNull @BsonProperty("userId") ObjectId userId, @NonNull @BsonProperty("idOfEntityReported") ObjectId idOfEntityReported,
    @NonNull @BsonProperty("content") String content, @BsonProperty("toAdmin") boolean toAdmin) {
        this.id = id;
        this.userId = userId;
        this.idOfEntityReported = idOfEntityReported;
        this.content = content;
        this.toAdmin = toAdmin;
    }
    public Report(@NonNull ObjectId userId, @NonNull ObjectId idOfEntityReported, @NonNull String content, boolean toAdmin) {
        this.id = null;
        this.userId = userId;
        this.idOfEntityReported = idOfEntityReported;
        this.content = content;
        this.toAdmin = toAdmin;
    }
    public Report(@NonNull User user, @NonNull ObjectId idOfEntityReported, @NonNull String content, boolean toAdmin) throws NullPointerException {
        this.id = null;
        ObjectId userId = user.getId();
        if (userId==null) throw new NullPointerException("User id equals null");
        this.userId = userId;
        this.idOfEntityReported = idOfEntityReported;
        this.content = content;
        this.toAdmin = toAdmin;
    }
    @BsonId
    final ObjectId id;
    @BsonProperty("userId")
    @NonNull
    final ObjectId userId;
    @NonNull
    @BsonProperty("idOfEntityReported")
    final ObjectId idOfEntityReported;
    @NonNull
    @BsonProperty("content")
    final String content;
    @BsonProperty("content")
    final boolean toAdmin;
}