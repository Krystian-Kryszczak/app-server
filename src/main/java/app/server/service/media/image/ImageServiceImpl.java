package app.server.service.media.image;

import app.server.model.media.image.Image;
import app.server.service.media.MediaServiceImpl;
import app.server.storage.repository.media.image.ImageRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.Binary;

@Singleton
public class ImageServiceImpl extends MediaServiceImpl<Image> implements ImageService {
    @Inject
    public ImageServiceImpl(ImageRepository imageRepository) {
        super(imageRepository);
    }
    @Override
    protected Image createMedia(@NonNull String name, @NonNull String creatorHexId, @Nullable Boolean isPrivate, @NonNull Binary binary) {
        return new Image(name, creatorHexId, isPrivate, binary);
    }
}