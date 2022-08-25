package app.server.service.media;

import app.server.model.media.Media;
import com.mongodb.client.result.InsertOneResult;
import io.micronaut.http.multipart.CompletedFileUpload;
import org.reactivestreams.Publisher;
import reactor.util.function.Tuple2;

import java.io.IOException;

public interface MediaService<T extends Media<T>> {
    Publisher<InsertOneResult> save(String creatorHexId, boolean isPrivate, CompletedFileUpload file) throws IOException;
    Publisher<Boolean> isPrivate(String hexId);
    Publisher<Tuple2<Boolean, String>> getIsPrivateAndCreatorHexId(String hexId);
    Publisher<T> findById(String hexId);
    Publisher<T> delete(String hexId);
}