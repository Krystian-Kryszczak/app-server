package app.server.service.media.audio;

import app.server.model.media.audio.Audio;
import app.server.service.media.MediaServiceImpl;
import app.server.storage.repository.media.audio.AudioRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.Binary;

@Singleton
public class AudioServiceImpl extends MediaServiceImpl<Audio> implements AudioService {
    @Inject
    public AudioServiceImpl(AudioRepository audioRepository) {
        super(audioRepository);
    }
    @Override
    protected Audio createMedia(@NonNull String name, @NonNull String creatorHexId, @Nullable Boolean isPrivate, @NonNull Binary binary) {
        return new Audio(name, creatorHexId, isPrivate, binary);
    }
}