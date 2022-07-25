package app.server.service.media.audio;

import app.server.model.media.audio.Audio;
import app.server.service.media.MediaService;
import app.server.storage.repository.media.audio.AudioRepository;
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
public class AudioService extends MediaService<Audio> {
    @Inject
    AudioRepository audioRepo;
    public Publisher<InsertOneResult> saveAudio(String creatorHexId, @NonNull CompletedFileUpload file) throws IOException {
        return audioRepo.save(new Audio(file.getFilename(), creatorHexId, new Binary(BsonBinarySubType.BINARY, file.getBytes())));
    }
    public Publisher<Audio> getAudio(String hexId) {
        return audioRepo.findById(hexId);
    }
    public Publisher<Audio> deleteAudio(String hexId) {
        return audioRepo.delete(hexId);
    }
}