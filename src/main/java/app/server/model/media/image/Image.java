package app.server.model.media.image;

import app.server.model.being.user.User;
import app.server.model.media.Media;
import app.server.service.media.image.ImageService;
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
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Introspected
public class Image extends Media<Image> {
    @Inject
    static ReportService reportService;
    @Inject
    static ImageService imageService;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Image(@Nullable @BsonId ObjectId hexId, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("creatorHexId") String creatorHexId,
                 @Nullable @BsonProperty("isPrivate") Boolean isPrivate, @NonNull @BsonProperty("dateTime") LocalDateTime dateTime,
                 @NonNull @BsonProperty("binary") Binary binary) {
        super(hexId, name, creatorHexId, isPrivate, dateTime, binary);
    }
    public Image(@NonNull String name, @NonNull String creatorHexId, @Nullable Boolean isPrivate, @NonNull Binary binary) {
        super(name, creatorHexId, isPrivate, binary);
    }
    @Deprecated
    public Image(@NonNull String name, @NonNull String creatorHexId, @Nullable Boolean isPrivate, @NonNull File file) throws IOException {
        super(name, creatorHexId, isPrivate, file);
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String getMediaUrl() {
        ObjectId id = getId();
        return id!=null ? "/images/"+id.toHexString()+"/" : "";
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        ObjectId id = getId();
        if (id==null) throw new NullPointerException("Image "+getName()+" hexId equals null.");
        return reportService.reportImage(id, user, content);
    }
    @Override
    public Publisher<Image> delete() {
        if (getId()==null) throw new NullPointerException("Image ID equal null.");
        return imageService.delete(getId().toHexString());
    }
}