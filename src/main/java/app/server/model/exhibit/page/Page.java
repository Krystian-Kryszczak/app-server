package app.server.model.exhibit.page;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.reactivestreams.Publisher;

import java.time.LocalDateTime;

@Introspected
public class Page extends Exhibit<Page> {
    @Creator
    @BsonCreator
    public Page(@NonNull @BsonId String hexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(hexId, rating, dateTime);
    }
    public Page() {
        super(); // hexId = null & rating = null
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        if (getHexId()==null) throw new NullPointerException(getClass().getSimpleName()+" hexId equals null.");
        return null; // TODO
    }
    @Override
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        if (getHexId()==null) throw new NullPointerException(getClass().getSimpleName()+" hexId equals null.");
        return null; // TODO
    }
}