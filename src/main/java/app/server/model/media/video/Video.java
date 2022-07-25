package app.server.model.media.video;

import app.server.model.being.user.User;
import app.server.model.media.Streamable;
import app.server.service.report.ReportService;
import app.server.service.media.video.VideoService;
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

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Introspected
public class Video extends Streamable<Video> {
    @Inject
    ReportService reportService;
    @Inject
    VideoService videoService;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Video(@Nullable @BsonId String hexId, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("creatorHexId") String creatorHexId,
                 @NonNull @BsonProperty("dateTime") LocalDateTime dateTime, @NonNull @BsonProperty("binary") Binary binary) {
        super(hexId, name, creatorHexId, dateTime, binary);
    }
    public Video(@NonNull String name, @NonNull String creatorHexId, @NonNull Binary binary) {
        super(name, creatorHexId, binary);
    }
    @Deprecated
    public Video(@NonNull String name, @NonNull String creatorHexId, @NonNull File file) throws IOException {
        super(name, creatorHexId, file);
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String getMediaUrl() {
        String hexId = getHexId();
        return hexId!=null ? "/videos/"+hexId+"/" : "";
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull @NotBlank String content) {
        String hexId = getHexId();
        if (hexId==null) throw new NullPointerException("Video "+getName()+" hexId equals null.");
        return reportService.reportVideo(hexId, user, content);
    }
    @Override
    public Publisher<Video> delete() {
        return videoService.deleteVideo(getHexId());
    }
}