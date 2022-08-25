package app.server.model.report.being.community.page;

import app.server.model.being.user.User;
import app.server.model.being.community.page.Page;
import app.server.model.report.being.community.CommunityReport;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Introspected
public class PageReport extends CommunityReport<Page> {
    @Creator
    @BsonCreator
    public PageReport(@BsonId ObjectId id, @NonNull @BsonProperty("userId") ObjectId userId, @NonNull @BsonProperty("idOfEntityReported") ObjectId idOfEntityReported,
    @NonNull @BsonProperty("content") String content, @BsonProperty("toAdmin") boolean toAdmin) {
        super(id, userId, idOfEntityReported, content, toAdmin);
    }
    public PageReport(@NonNull ObjectId userId, @NonNull ObjectId idOfEntityReported, @NonNull String content, boolean toAdmin) {
        super(userId, idOfEntityReported, content, toAdmin);
    }
    public PageReport(@NonNull User user, @NonNull ObjectId idOfEntityReported, @NonNull String content, boolean toAdmin) throws NullPointerException {
        super(user, idOfEntityReported, content, toAdmin);
    }
}