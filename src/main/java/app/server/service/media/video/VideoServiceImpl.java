package app.server.service.media.video;

import app.server.model.media.video.Video;
import app.server.service.media.MediaServiceImpl;
import app.server.storage.repository.media.video.VideoRepository;
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
public class VideoServiceImpl extends MediaServiceImpl<Video> implements VideoService {
    @Inject
    VideoRepository videoRepository;
    public Publisher<InsertOneResult> saveVideo(String creatorHexId, @NonNull CompletedFileUpload file) throws IOException {
        return videoRepository.save(new Video(file.getFilename(), creatorHexId, new Binary(BsonBinarySubType.BINARY, file.getBytes())));
    }
    public Publisher<Video> getVideo(String hexId) {
        return videoRepository.findById(hexId);
    }
    public Publisher<Video> deleteVideo(String hexId) {
        return videoRepository.delete(hexId);
    }
}