package app.server.service.media.audio;

import app.server.model.media.audio.Audio;
import app.server.service.media.MediaService;
import com.mongodb.client.result.InsertOneResult;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.multipart.CompletedFileUpload;
import org.reactivestreams.Publisher;

import java.io.IOException;

public interface AudioService extends MediaService<Audio> {
    Publisher<InsertOneResult> saveAudio(String creatorHexId, @NonNull CompletedFileUpload file) throws IOException;
    Publisher<Audio> getAudio(String hexId);
    Publisher<Audio> deleteAudio(String hexId);
}