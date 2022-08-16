package app.server.service.media.image;

import app.server.model.media.image.Image;
import app.server.service.media.MediaService;
import com.mongodb.client.result.InsertOneResult;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.multipart.CompletedFileUpload;
import org.reactivestreams.Publisher;

import java.io.IOException;

public interface ImageService extends MediaService<Image> {}