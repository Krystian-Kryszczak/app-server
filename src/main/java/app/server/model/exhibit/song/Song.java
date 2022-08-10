package app.server.model.exhibit.song;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Introspected
public class Song extends Exhibit<Song> {
    @NonNull
    @NotBlank
    @BsonProperty("audio")
    final String audio;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Song(@NonNull @BsonId ObjectId hexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("datetime") LocalDateTime dateTime, @NonNull @BsonProperty("audio") String audio) {
        super(hexId, rating, dateTime);
        this.audio = audio;
    }
    public Song(@NonNull @BsonProperty("audio") String audio) {
        super(); // hexId = null & rating = null
        this.audio = audio;
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String getUrl() {
        return abstractUrl("songs");
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        return getReport().reportSong(getId(), user, content);
    }
    @Override
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        return getReport().reportSongToAdmin(getId(), user, content);
    }
}