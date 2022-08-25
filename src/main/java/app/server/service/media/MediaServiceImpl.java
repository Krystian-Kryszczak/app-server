package app.server.service.media;

import app.server.model.media.Media;
import app.server.storage.repository.media.MediaRepository;
import com.mongodb.client.result.InsertOneResult;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.multipart.CompletedFileUpload;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.reactivestreams.Publisher;
import reactor.util.function.Tuple2;

import java.io.IOException;

public abstract class MediaServiceImpl<T extends Media<T>> implements MediaService<T> {
    private final MediaRepository<T> mediaRepository;
    protected MediaServiceImpl(MediaRepository<T> mediaRepository) {
        this.mediaRepository = mediaRepository;
    }
    public Publisher<InsertOneResult> save(String creatorHexId, boolean isPrivate, CompletedFileUpload file) throws IOException {
        return mediaRepository.save(createMedia(file.getFilename(), creatorHexId, isPrivate, new Binary(BsonBinarySubType.BINARY, file.getBytes())));
    }
    public Publisher<Boolean> isPrivate(String hexId) {
        return mediaRepository.isPrivate(hexId);
    }
    public Publisher<Tuple2<Boolean, String>> getIsPrivateAndCreatorHexId(String hexId) {
        return mediaRepository.getIsPrivateAndCreatorHexId(hexId);
    }
    public Publisher<T> findById(String hexId) {
        return mediaRepository.findById(hexId);
    }
    public Publisher<T> delete(String hexId) {
        return mediaRepository.delete(hexId);
    }
    protected abstract T createMedia(@NonNull String name, @NonNull String creatorHexId, @Nullable Boolean isPrivate, @NonNull Binary binary);
}