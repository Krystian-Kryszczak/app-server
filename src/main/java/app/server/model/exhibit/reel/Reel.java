package app.server.model.exhibit.reel;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import app.server.service.exhibit.ExhibitService;
import app.server.service.exhibit.reel.ReelService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Introspected
public class Reel extends Exhibit<Reel> {
    @Inject
    static ReelService reelService;
    @NonNull
    @NotBlank
    @BsonProperty("video")
    String video;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Reel(@NonNull @BsonId ObjectId hexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("video") String video, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(hexId, rating, dateTime);
        this.video = video;
    }
    public Reel(@NonNull @BsonProperty("video") String video) {
        super(); // hexId = null & rating = null
        this.video = video;
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String getUrl() {
        return abstractUrl("reels");
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        return getReport().reportReel(getId(), user, content);
    }
    @Override
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        return getReport().reportReelToAdmin(getId(), user, content);
    }
    @Override
    public Flux<String> getHistory(@NonNull String userHexId) {
        return getExhibitHistoryService().getReelHistory(getId().toHexString(), userHexId);
    }
    @Override
    protected ExhibitService<Reel> getExhibitService() {
        return reelService;
    }
}