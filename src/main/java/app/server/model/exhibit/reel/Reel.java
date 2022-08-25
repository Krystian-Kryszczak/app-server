package app.server.model.exhibit.reel;

import app.server.model.being.user.User;
import app.server.model.exhibit.ExhibitType;
import app.server.model.exhibit.MediaExhibit;
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

import java.time.LocalDateTime;

@Introspected
public class Reel extends MediaExhibit<Reel> {
    @Inject
    static ReelService reelService;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Reel(@NonNull @BsonId ObjectId hexId, @NonNull @BsonProperty("userHexId") String userHexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("media") String media, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(hexId, userHexId, rating, media, dateTime);
    }
    public Reel(@NonNull String userHexId, @NonNull String media) {
        super(userHexId, media); // hexId = null & rating = null
    }
    // ---------------------------------------------------------------------------------------------------- //
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

    @Override
    protected ExhibitType getType() {
        return ExhibitType.reel;
    }
}