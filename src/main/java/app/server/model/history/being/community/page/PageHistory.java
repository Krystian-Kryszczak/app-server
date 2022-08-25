package app.server.model.history.being.community.page;

import app.server.model.being.community.page.Page;
import app.server.model.history.being.community.CommunityHistory;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Introspected
public class PageHistory extends CommunityHistory<Page> {
    @Creator
    @BsonCreator
    protected PageHistory(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("user") ObjectId userId, @NonNull @BsonProperty("target") ObjectId target, @Nullable String content, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(id, userId, target, content, dateTime);
    }
    protected PageHistory(@NonNull ObjectId userId, @NonNull ObjectId target, @Nullable String content) {
        super(userId, target, content);
    }
}