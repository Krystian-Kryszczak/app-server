package app.server.model.report.being;

import app.server.model.being.Being;
import app.server.model.being.user.User;
import app.server.model.report.Report;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public abstract class BeingReport<T extends Being> extends Report {
    @Creator
    @BsonCreator
    public BeingReport(@BsonId ObjectId id, @NonNull @BsonProperty("userId") ObjectId userId, @NonNull @BsonProperty("idOfEntityReported") ObjectId idOfEntityReported,
                       @NonNull @BsonProperty("content") String content, @BsonProperty("toAdmin") boolean toAdmin) {
        super(id, userId, idOfEntityReported, content, toAdmin);
    }
    public BeingReport(@NonNull ObjectId userId, @NonNull ObjectId idOfEntityReported, @NonNull String content, boolean toAdmin) {
        super(userId, idOfEntityReported, content, toAdmin);
    }
    public BeingReport(@NonNull User user, @NonNull ObjectId idOfEntityReported, @NonNull String content, boolean toAdmin) throws NullPointerException {
        super(user, idOfEntityReported, content, toAdmin);
    }
}