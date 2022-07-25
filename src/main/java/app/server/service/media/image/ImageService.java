package app.server.service.media.image;

import app.server.model.media.image.Image;
import app.server.service.media.MediaService;
import app.server.storage.repository.media.image.ImageRepository;
import com.mongodb.client.result.InsertOneResult;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.reactivestreams.Publisher;

import java.io.IOException;

@Singleton
public class ImageService extends MediaService<Image> {
    @Inject
    private ImageRepository imageRepo;
    public Publisher<InsertOneResult> saveImage(String creatorHexId, @NonNull CompletedFileUpload file) throws IOException {
        return imageRepo.save(new Image(file.getFilename(), creatorHexId, new Binary(BsonBinarySubType.BINARY, file.getBytes())));
    }
    public Publisher<Image> getImage(String hexId) {
        return imageRepo.findById(hexId);
    }
    public Publisher<Image> deleteImage(String hexId) {
        return imageRepo.delete(hexId);
    }
}