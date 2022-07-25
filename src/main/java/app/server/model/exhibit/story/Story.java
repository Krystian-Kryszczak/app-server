package app.server.model.exhibit.story;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.reactivestreams.Publisher;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Introspected
public class Story extends Exhibit<Story> {
    @NonNull
    @NotBlank
    @BsonProperty("media")
    final String media;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Story(@NonNull @BsonId String hexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("media") String media, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(hexId, rating, dateTime);
        this.media = media;
    }
    public Story(@NonNull @BsonProperty("media") String media) {
        super(); // hexId = null & rating = null
        this.media = media;
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String getUrl() {
        return abstractUrl("stories");
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        return getReport().reportStory(getHexId(), user, content);
    }
    @Override
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        return getReport().reportStoryToAdmin(getHexId(), user, content);
    }
}