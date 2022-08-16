package app.server.service.media.video;

import app.server.model.media.video.Video;
import app.server.service.media.MediaServiceImpl;
import app.server.storage.repository.media.video.VideoRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.Binary;

@Singleton
public class VideoServiceImpl extends MediaServiceImpl<Video> implements VideoService {
    @Inject
    public VideoServiceImpl(VideoRepository videoRepository) {
        super(videoRepository);
    }
    @Override
    protected Video createMedia(@NonNull String name, @NonNull String creatorHexId, @Nullable Boolean isPrivate, @NonNull Binary binary) {
        return new Video(name, creatorHexId, isPrivate, binary);
    }
}