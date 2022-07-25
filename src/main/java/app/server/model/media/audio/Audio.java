package app.server.model.media.audio;

import app.server.model.being.user.User;
import app.server.model.media.Streamable;
import app.server.service.media.audio.AudioService;
import app.server.service.report.ReportService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.Binary;
import org.reactivestreams.Publisher;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Introspected
public class Audio extends Streamable<Audio> {
    @Inject
    static ReportService reportService;
    @Inject
    static AudioService audioService;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Audio(@Nullable @BsonId String hexId, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("creatorHexId") String creatorHexId,
                 @NonNull @BsonProperty("dateTime") LocalDateTime dateTime, @NonNull @BsonProperty("binary") Binary binary) {
        super(hexId, name, creatorHexId, dateTime, binary);
    }
    public Audio(@NonNull String name, @NonNull String creatorHexId, @NonNull Binary binary) {
        super(name, creatorHexId, binary);
    }
    @Deprecated
    public Audio(@NonNull String name, @NonNull String creatorHexId, @NonNull File file) throws IOException {
        super(name, creatorHexId, file);
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String getMediaUrl() {
        String hexId = getHexId();
        return hexId!=null ? "/audio/"+hexId+"/" : "";
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        String hexId = getHexId();
        if (hexId==null) throw new NullPointerException("Audio "+getName()+" hexId equals null.");
        return reportService.reportAudio(hexId, user, content);
    }
    @Override
    public Publisher<Audio> delete() {
        return audioService.deleteAudio(getHexId());
    }
}